package com.zhou.view.controller;

import com.zhou.config.ReOfferConfig;
import com.zhou.utils.ReOfferA;
import com.zhou.view.utils.MyAppUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class MainController {

    @FXML
    private Label msg;

    @FXML
    private TextArea one;

    @FXML
    private DatePicker datePic;

    private ReOfferA reOfferA = new ReOfferA();

    @FXML
    private TextArea three;

    @FXML
    private TextArea two;


    public void initialize(){
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(nowTime);
        datePic.setValue(LocalDate.parse(getClByDate(nowTime)));
    }
    @FXML
    void onSubmit(ActionEvent event) {
        getClByDate(datePic.getValue().toString());
    }

    private String getClByDate(String dateYYYYMMDD) {
        List<Integer> yearMonthDay = MyAppUtils.splictYearMonthDay(dateYYYYMMDD);
        String dateString = String.format("%04d-%02d", yearMonthDay.get(0), yearMonthDay.get(1));
        List<ReOfferA.Holiday> holidayList = reOfferA.getHolidays(dateString);
        if (yearMonthDay.get(2) > 20) {
            holidayList.addAll(reOfferA.getHolidays(String.format("%04d-%02d", yearMonthDay.get(0), yearMonthDay.get(1) + 1)));
        }
        if (yearMonthDay.get(2) < 10) {
            holidayList.addAll(0, reOfferA.getHolidays(String.format("%04d-%02d", yearMonthDay.get(0), yearMonthDay.get(1) - 1)));
        }
        if (yearMonthDay.get(1) > 11) {
            holidayList.addAll(reOfferA.getHolidays(String.format("%04d-%02d", yearMonthDay.get(0) + 1, yearMonthDay.get(1))));
        }
        if (yearMonthDay.get(1) < 1) {
            holidayList.addAll(0, reOfferA.getHolidays(String.format("%04d-%02d", yearMonthDay.get(0) - 1, yearMonthDay.get(1))));
        }
        HashMap<String, Integer> holidayMap = IntStream.range(0, holidayList.size()).boxed().collect(Collectors.toMap(i -> holidayList.get(i).getDate(), i -> i, (existing, replacement) -> existing, // 处理键冲突的策略
                HashMap::new));
        //找到当前日期的索引
        int diffCount = 0;
        int holiIndex = holidayMap.get(dateYYYYMMDD);
        while (holidayList.get(holiIndex).getStatus() != 0) {
            //不是工作日
            if (holidayList.get(holiIndex - diffCount).getStatus() != 0) {
                diffCount++;
            }
            holiIndex = holiIndex - diffCount;
        }

        List<ReOfferA.Holiday> notHolidays = holidayList.stream().filter(item -> item.getStatus() == 0).collect(Collectors.toList());
        HashMap<String, Integer> notHolidaysMap = IntStream.range(0, notHolidays.size()).boxed().collect(Collectors.toMap(i -> notHolidays.get(i).getDate(), i -> i, (existing, replacement) -> existing, // 处理键冲突的策略
                HashMap::new));
        Integer endIndex = notHolidaysMap.get(holidayList.get(holiIndex).getDate());
        if (diffCount > 0) {
            msg.setText("日期为节假日,选择上一个工作日" + notHolidays.get(endIndex).getDate1());
        }else {
            msg.setText("");
        }
        one.setText(ReOfferConfig.lowPacketCondition.replace("{t}", notHolidays.get(endIndex).getDate1()).replace("{t-1}", notHolidays.get(endIndex - 1).getDate1()));
        two.setText(ReOfferConfig.lowPacketCondition.replace("{t}", notHolidays.get(endIndex - 1).getDate1()).replace("{t-1}", notHolidays.get(endIndex - 2).getDate1()));
        three.setText(ReOfferConfig.lowPacketCondition.replace("{t}", notHolidays.get(endIndex - 2).getDate1()).replace("{t-1}", notHolidays.get(endIndex - 3).getDate1()));
        ;

        return  notHolidays.get(endIndex).getDate();
    }

}
