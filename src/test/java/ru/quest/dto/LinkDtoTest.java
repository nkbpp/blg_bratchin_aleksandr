package ru.quest.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LinkDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        LinkDto linkDto = new LinkDto();
        linkDto.setLink("https//");
        linkDto.setLinkId(1L);

        Set<ConstraintViolation<LinkDto>> violations = validator.validate(linkDto);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        LinkDto linkDto = new LinkDto();

        Set<ConstraintViolation<LinkDto>> violations = validator.validate(linkDto);

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
        LinkDto linkDto = new LinkDto();
        linkDto.setLink("   ");
        linkDto.setLinkId(0L);

        Set<ConstraintViolation<LinkDto>> violations = validator.validate(linkDto);

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