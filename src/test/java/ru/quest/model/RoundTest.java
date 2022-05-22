package ru.quest.model;

import org.junit.jupiter.api.Test;
import ru.quest.model.level.Level;
import ru.quest.model.quest.Quest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        Round round = new Round();
        round.setRoundId(1L);
        round.setIndex(1);
        round.setName("name");
        Quest quest = new Quest();
        quest.setQuestId(1L);
        quest.setTextQuest("TextQuest");
        quest.setLevel(Level.MEDIUM);
        Answer answer = new Answer();
        answer.setAnswer("answer");
        answer.setAnswerId(1L);
        answer.setCorrectAnswer(true);
        quest.setAnswers(List.of(answer));
        round.setQuests(asList(quest));

        Set<ConstraintViolation<Round>> violations = validator.validate(round);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        Round round = new Round();

        Set<ConstraintViolation<Round>> violations = validator.validate(round);

        assertThat(violations.size()).isEqualTo(4);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("name") &&
                                testObjectConstraintViolation.getMessage().equals("Name round cannot be blank"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("index") &&
                                testObjectConstraintViolation.getMessage().equals("index cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("roundId") &&
                                testObjectConstraintViolation.getMessage().equals("roundId cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("quests") &&
                                testObjectConstraintViolation.getMessage().equals("quests cannot be null"));
    }

    @Test
    public void whenAllInvalid() {
        Round round = new Round();
        round.setRoundId(0L);
        round.setIndex(0);
        round.setName("   ");

        Set<ConstraintViolation<Round>> violations = validator.validate(round);

        assertThat(violations.size()).isEqualTo(4);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("quests") &&
                                testObjectConstraintViolation.getMessage().equals("quests cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("name") &&
                                testObjectConstraintViolation.getMessage().equals("Name round cannot be blank"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("index") &&
                                testObjectConstraintViolation.getMessage().equals("index must be greater than 1"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("roundId") &&
                                testObjectConstraintViolation.getMessage().equals("roundId must be greater than 1"));
    }

}