package com.zhou.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author BJB314
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Constraint(validatedBy = TestValidCheck.class)
public @interface TestValid {
    String message() default "消息错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
