package com.zhou.view.utils;

import com.zhou.OthWorkApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyAppUtils {

    public static String BASE_PATH = "view/";
    public static void toChangeView(Scene scene, String viewName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(OthWorkApplication.class.getResource(BASE_PATH + "main.fxml"));
            scene.setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("跳转异常");
        }
    }

    public static List<Integer> splictYearMonthDay(String dateStringYYYYMMDD) {
        return Arrays.stream(dateStringYYYYMMDD.split("-")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
