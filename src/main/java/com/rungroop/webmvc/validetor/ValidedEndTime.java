package com.rungroop.webmvc.validetor;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EndTimeAfterStartTimeValidator.class)
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidedEndTime {
    String message() default "End Time must be after Start Time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
