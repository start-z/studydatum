package com.zhou;

import com.zhou.view.controller.LoginController;
import com.zhou.view.utils.MyAppUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhouhelong
 * @creat 2022-06-10 17:02
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OthWorkApplication extends Application {

    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = new SpringApplicationBuilder(OthWorkApplication.class).run();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( MyAppUtils.BASE_PATH + "main.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("我的策略!");
        stage.setScene(scene);
        stage.show();
    }
}
