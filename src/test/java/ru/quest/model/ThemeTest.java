package ru.quest.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ThemeTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        Theme theme = new Theme();
        theme.setThemeId(1L);
        theme.setTheme("theme");

        Set<ConstraintViolation<Theme>> violations = validator.validate(theme);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        Theme theme = new Theme();
        Set<ConstraintViolation<Theme>> violations = validator.validate(theme);

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
        Theme theme = new Theme();
        theme.setThemeId(0L);
        theme.setTheme("  ");

        Set<ConstraintViolation<Theme>> violations = validator.validate(theme);

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