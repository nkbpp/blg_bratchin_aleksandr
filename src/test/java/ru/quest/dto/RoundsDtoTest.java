package ru.quest.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import ru.quest.model.level.Level;

class RoundsDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        RoundsDto roundsDto = new RoundsDto();
        roundsDto.setName("name");
        roundsDto.setCount(5);
        roundsDto.setIndex(1);
        roundsDto.setLevel(Level.MEDIUM);

        Set<ConstraintViolation<RoundsDto>> violations = validator.validate(roundsDto);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        RoundsDto roundsDto = new RoundsDto();

        Set<ConstraintViolation<RoundsDto>> violations = validator.validate(roundsDto);

        assertThat(violations.size()).isEqualTo(4);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("count") &&
                                testObjectConstraintViolation.getMessage().equals("Questions cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("level") &&
                                testObjectConstraintViolation.getMessage().equals("level cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("name") &&
                                testObjectConstraintViolation.getMessage().equals("Round name not be blank"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("index") &&
                                testObjectConstraintViolation.getMessage().equals("Index cannot be null"));
    }

    @Test
    public void whenAllInvalid() {
        RoundsDto roundsDto = new RoundsDto();
        roundsDto.setName("   ");
        roundsDto.setCount(0);
        roundsDto.setIndex(0);
        roundsDto.setLevel(null);

        Set<ConstraintViolation<RoundsDto>> violations = validator.validate(roundsDto);

        assertThat(violations.size()).isEqualTo(4);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("count") &&
                                testObjectConstraintViolation.getMessage().equals("Questions must be greater than 1"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("level") &&
                                testObjectConstraintViolation.getMessage().equals("level cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("name") &&
                                testObjectConstraintViolation.getMessage().equals("Round name not be blank"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("index") &&
                                testObjectConstraintViolation.getMessage().equals("Index must be greater than 1"));
    }


}