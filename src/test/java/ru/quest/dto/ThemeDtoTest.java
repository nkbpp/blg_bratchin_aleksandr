package ru.quest.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ThemeDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        ThemeDto themeDto = new ThemeDto();
        themeDto.setThemeId(1L);
        themeDto.setTheme("theme");

        Set<ConstraintViolation<ThemeDto>> violations = validator.validate(themeDto);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        ThemeDto themeDto = new ThemeDto();
        Set<ConstraintViolation<ThemeDto>> violations = validator.validate(themeDto);

        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("themeId") &&
                                testObjectConstraintViolation.getMessage().equals("themeId cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("theme") &&
                                testObjectConstraintViolation.getMessage().equals("Theme cannot be blank"));
    }

    @Test
    public void whenAllInvalid() {
        ThemeDto themeDto = new ThemeDto();
        themeDto.setThemeId(0L);
        themeDto.setTheme("  ");

        Set<ConstraintViolation<ThemeDto>> violations = validator.validate(themeDto);

        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("theme") &&
                                testObjectConstraintViolation.getMessage().equals("Theme cannot be blank"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("themeId") &&
                                testObjectConstraintViolation.getMessage().equals("themeId must be greater than 1"));
    }

}