package ru.quest.model.quest;

import org.junit.jupiter.api.Test;
import ru.quest.dto.quest.CorrectAnswerDtoExists;
import ru.quest.model.Answer;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CorrectAnswerExistsValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private class TestObject {

        @CorrectAnswerExists
        private List<@Valid Answer> answers;

        public TestObject() {
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }
    }

    @Test
    public void whenCorrectAnswerIsTrue() {
        TestObject testObject = new TestObject();
        Answer answer = new Answer();
        answer.setAnswer("answer");
        answer.setAnswerId(1L);
        answer.setCorrectAnswer(true);
        testObject.setAnswers(List.of(answer));

        Set<ConstraintViolation<TestObject>> violations = validator.validate(testObject);

        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenCorrectAnswerIsFalse() {
        TestObject testObject = new TestObject();
        Answer answer = new Answer();
        answer.setAnswer("answer");
        answer.setAnswerId(1L);
        answer.setCorrectAnswer(false);
        testObject.setAnswers(List.of(answer));

        Set<ConstraintViolation<TestObject>> violations = validator.validate(testObject);

        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answers") &&
                                testObjectConstraintViolation.getMessage().equals("Correct answer not exists")
        );
    }

}