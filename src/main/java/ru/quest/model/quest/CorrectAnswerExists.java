package ru.quest.model.quest;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CorrectAnswerExistsValidator.class)
public @interface CorrectAnswerExists {
    String message() default "Correct answer not exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
