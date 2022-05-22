package ru.quest.model.quest;

import org.junit.jupiter.api.Test;
import ru.quest.model.Answer;
import ru.quest.model.level.Level;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class QuestTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        Quest quest = new Quest();
        quest.setQuestId(1L);
        quest.setTextQuest("TextQuest");
        quest.setLevel(Level.MEDIUM);
        Answer answer = new Answer();
        answer.setAnswer("answer");
        answer.setAnswerId(1L);
        answer.setCorrectAnswer(true);
        quest.setAnswers(List.of(answer));

        Set<ConstraintViolation<Quest>> violations = validator.validate(quest);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        Quest quest = new Quest();

        Set<ConstraintViolation<Quest>> violations = validator.validate(quest);

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
        Quest quest = new Quest();
        quest.setQuestId(0L);
        quest.setTextQuest("   ");
        quest.setLevel(null);
        Answer answer = new Answer();
        answer.setAnswer("answer");
        answer.setAnswerId(1L);
        answer.setCorrectAnswer(false);
        quest.setAnswers(List.of(answer));

        Set<ConstraintViolation<Quest>> violations = validator.validate(quest);

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