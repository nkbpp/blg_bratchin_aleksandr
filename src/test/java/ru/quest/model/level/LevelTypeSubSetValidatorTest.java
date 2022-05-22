package ru.quest.model.level;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class LevelTypeSubSetValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private class TestObject {

        @LevelTypeSubset(anyOf = {Level.EASY, Level.MEDIUM})
        private Level level;

        public TestObject() {
        }

        public void setLevel(Level level) {
            this.level = level;
        }

    }

    @Test
    public void whenEnumAnyOfSubset() {
        TestObject testObject = new TestObject();
        testObject.setLevel(Level.MEDIUM);

        Set<ConstraintViolation<TestObject>> violations = validator.validate(testObject);
        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenEnumNotAnyOfSubset() {
        TestObject testObject = new TestObject();
        testObject.setLevel(Level.HARD);
        Set<ConstraintViolation<TestObject>> violations = validator.validate(testObject);

        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("level") &&
                        testObjectConstraintViolation.getMessage().equals("must be any of [EASY, MEDIUM]")
        );
    }

}