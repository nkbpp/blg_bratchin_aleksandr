package ru.quest.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LinkTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        Link link = new Link();
        link.setLink("https//");
        link.setLinkId(1L);

        Set<ConstraintViolation<Link>> violations = validator.validate(link);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        Link link = new Link();

        Set<ConstraintViolation<Link>> violations = validator.validate(link);

        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("linkId") &&
                                testObjectConstraintViolation.getMessage().equals("linkId cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("link") &&
                                testObjectConstraintViolation.getMessage().equals("Link cannot be blank"));
    }

    @Test
    public void whenAllInvalid() {
        Link link = new Link();
        link.setLink("   ");
        link.setLinkId(0L);

        Set<ConstraintViolation<Link>> violations = validator.validate(link);

        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("linkId") &&
                                testObjectConstraintViolation.getMessage().equals("linkId must be greater than 1"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("link") &&
                                testObjectConstraintViolation.getMessage().equals("Link cannot be blank"));
    }

}