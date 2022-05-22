package ru.quest.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswer("answer");
        answerDto.setAnswerId(1L);
        answerDto.setCorrectAnswer(true);

        Set<ConstraintViolation<AnswerDto>> violations = validator.validate(answerDto);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        AnswerDto answerDto = new AnswerDto();

        Set<ConstraintViolation<AnswerDto>> violations = validator.validate(answerDto);

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
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswer("   ");
        answerDto.setAnswerId(0L);
        answerDto.setCorrectAnswer(null);

        Set<ConstraintViolation<AnswerDto>> violations = validator.validate(answerDto);

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