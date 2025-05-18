package com.zhou.view.controller;

import com.zhou.view.utils.MyAppUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private Label errorMsg;

    @FXML
    void onSubmit(ActionEvent event) {
        if (password.getText().equals("171935")) {
            MyAppUtils.toChangeView(password.getScene(), "main");
        } else {
            errorMsg.setText("登录失败");
        }
    }

}
