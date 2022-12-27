package com.zhou.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
@Component
public class I18nUtils {
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
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message, args, locale);

    }
}
