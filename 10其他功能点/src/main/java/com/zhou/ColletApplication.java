package com.zhou;

import com.zhou.jsoup.JsoupDemo;
import com.zhou.utils.I18nUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ColletApplication {
    public static void main(String[] args) {
        SpringApplication.run(ColletApplication.class, args);

    }

    @Autowired
    private I18nUtils i18nUtils;

    @Bean  //项目启动完
    public ApplicationRunner initRunner() {
        JsoupDemo jsoupDemo = new JsoupDemo();
        Class<? extends JsoupDemo> demoClass = jsoupDemo.getClass();
        String english = i18nUtils.getEnglish("com.zhou.demo");
        return args -> System.out.println("系统启动成功1");
    }
    @PostConstruct  //bean 加载完
    public void initRunnerOne() {
        System.out.println("系统启动成功2");
    }
}
