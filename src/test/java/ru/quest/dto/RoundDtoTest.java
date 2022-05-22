package ru.quest.dto;

import org.junit.jupiter.api.Test;
import ru.quest.dto.quest.QuestDto;
import ru.quest.model.level.Level;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RoundDtoTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        RoundDto roundDto = new RoundDto();
        roundDto.setRoundId(1L);
        roundDto.setIndex(1);
        roundDto.setName("name");
        QuestDto questDto = new QuestDto();
        questDto.setQuestId(1L);
        questDto.setTextQuest("TextQuest");
        questDto.setLevel(Level.MEDIUM);
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswer("answer");
        answerDto.setAnswerId(1L);
        answerDto.setCorrectAnswer(true);
        questDto.setAnswers(List.of(answerDto));
        roundDto.setQuests(List.of(questDto));

        Set<ConstraintViolation<RoundDto>> violations = validator.validate(roundDto);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        RoundDto roundDto = new RoundDto();

        Set<ConstraintViolation<RoundDto>> violations = validator.validate(roundDto);

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
        RoundDto roundDto = new RoundDto();
        roundDto.setRoundId(0L);
        roundDto.setIndex(0);
        roundDto.setName("   ");

        Set<ConstraintViolation<RoundDto>> violations = validator.validate(roundDto);

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