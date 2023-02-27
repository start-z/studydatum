package com.zhou.annotation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zhou
 * @since 2023/2/27
 * description:
 */
@Component
public class TestValidCheck implements ConstraintValidator<TestValid, String> {
    @Override
    public void initialize(TestValid testValid) {
        System.out.println("自定义拦截注解在初始化");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("s===>"+s);
        System.out.println("constraintValidatorContext===>"+constraintValidatorContext);
        return false;
    }
}
