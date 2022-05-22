package ru.quest.dto.quest;

import org.junit.jupiter.api.Test;
import ru.quest.dto.AnswerDto;
import ru.quest.model.level.Level;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class QuestDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        QuestDto questDto = new QuestDto();
        questDto.setQuestId(1L);
        questDto.setTextQuest("TextQuest");
        questDto.setLevel(Level.MEDIUM);
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswer("answer");
        answerDto.setAnswerId(1L);
        answerDto.setCorrectAnswer(true);
        questDto.setAnswers(List.of(answerDto));

        Set<ConstraintViolation<QuestDto>> violations = validator.validate(questDto);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        QuestDto questDto = new QuestDto();

        Set<ConstraintViolation<QuestDto>> violations = validator.validate(questDto);

        assertThat(violations.size()).isEqualTo(5);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answers") &&
                                testObjectConstraintViolation.getMessage().equals("answers cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answers") &&
                                testObjectConstraintViolation.getMessage().equals("Correct answer not exists"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("questId") &&
                                testObjectConstraintViolation.getMessage().equals("questId cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("level") &&
                                testObjectConstraintViolation.getMessage().equals("level cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("textQuest") &&
                                testObjectConstraintViolation.getMessage().equals("Text quest cannot be blank"));
    }

    @Test
    public void whenAllInvalid() {
        QuestDto questDto = new QuestDto();
        questDto.setQuestId(0L);
        questDto.setTextQuest("   ");
        questDto.setLevel(null);
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswer("answer");
        answerDto.setAnswerId(1L);
        answerDto.setCorrectAnswer(false);
        questDto.setAnswers(List.of(answerDto));

        Set<ConstraintViolation<QuestDto>> violations = validator.validate(questDto);

        assertThat(violations.size()).isEqualTo(4);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("answers") &&
                                testObjectConstraintViolation.getMessage().equals("Correct answer not exists"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("questId") &&
                                testObjectConstraintViolation.getMessage().equals("questId must be greater than 1"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("textQuest") &&
                                testObjectConstraintViolation.getMessage().equals("Text quest cannot be blank"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("level") &&
                                testObjectConstraintViolation.getMessage().equals("level cannot be null"));
    }

}