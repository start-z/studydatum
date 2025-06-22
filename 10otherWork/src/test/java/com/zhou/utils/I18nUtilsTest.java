package com.zhou.utils;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(value = SpringRunner.class)
class I18nUtilsTest {
    @Autowired
    private I18nUtils i18nUtils;

    @Test
    void getEnglish() {
        System.out.println(i18nUtils.getEnglish("com.zhou.demo"));
    }
}
