package ru.quest.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        Answer answer = new Answer();
        answer.setAnswer("answer");
        answer.setAnswerId(1L);
        answer.setCorrectAnswer(true);

        Set<ConstraintViolation<Answer>> violations = validator.validate(answer);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        Answer answer = new Answer();

        Set<ConstraintViolation<Answer>> violations = validator.validate(answer);

        assertThat(violations.size()).isEqualTo(3);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answerId") &&
                                testObjectConstraintViolation.getMessage().equals("answerId cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("correctAnswer") &&
                                testObjectConstraintViolation.getMessage().equals("Must be set"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answer") &&
                                testObjectConstraintViolation.getMessage().equals("Answer cannot be blank"));
    }

    @Test
    public void whenAllInvalid() {
        Answer answer = new Answer();
        answer.setAnswer("   ");
        answer.setAnswerId(0L);
        answer.setCorrectAnswer(null);

        Set<ConstraintViolation<Answer>> violations = validator.validate(answer);

        assertThat(violations.size()).isEqualTo(3);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answerId") &&
                                testObjectConstraintViolation.getMessage().equals("answerId must be greater than 1"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("correctAnswer") &&
                                testObjectConstraintViolation.getMessage().equals("Must be set"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answer") &&
                                testObjectConstraintViolation.getMessage().equals("Answer cannot be blank"));
    }

}