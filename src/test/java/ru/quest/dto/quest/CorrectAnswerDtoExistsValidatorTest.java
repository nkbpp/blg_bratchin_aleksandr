package ru.quest.dto.quest;

import org.junit.jupiter.api.Test;
import ru.quest.dto.AnswerDto;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CorrectAnswerDtoExistsValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private class TestObject {

        @CorrectAnswerDtoExists
        private List<@Valid AnswerDto> answers;

        public TestObject() {
        }

        public void setAnswers(List<AnswerDto> answers) {
            this.answers = answers;
        }
    }

    @Test
    public void whenCorrectAnswerIsTrue() {
        TestObject testObject = new TestObject();
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswer("answer");
        answerDto.setAnswerId(1L);
        answerDto.setCorrectAnswer(true);
        testObject.setAnswers(Arrays.asList(answerDto));

        Set<ConstraintViolation<TestObject>> violations = validator.validate(testObject);

        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenCorrectAnswerIsFalse() {
        TestObject testObject = new TestObject();
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswer("answer");
        answerDto.setAnswerId(1L);
        answerDto.setCorrectAnswer(false);
        testObject.setAnswers(Arrays.asList(answerDto));

        Set<ConstraintViolation<TestObject>> violations = validator.validate(testObject);

        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answers") &&
                                testObjectConstraintViolation.getMessage().equals("Correct answer not exists")
        );
    }

}