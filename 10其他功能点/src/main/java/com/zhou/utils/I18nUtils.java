package com.zhou.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Locale;


@Component
public class I18nUtils implements InvocationHandler {
    @Autowired
    private MessageSource messageSource;

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getEnglish(String message, Object... args) {
        Locale aDefault = Locale.getDefault();
        Locale english = new Locale("en_US");
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message, args, english);

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(args);
    }
}
