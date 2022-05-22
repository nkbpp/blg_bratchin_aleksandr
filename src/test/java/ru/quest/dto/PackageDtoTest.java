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

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;

class PackageDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        PackageDto aPackage = new PackageDto();
        aPackage.setPackageId(1L);
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
        roundDto.setQuests(asList(questDto));
        aPackage.setRounds(asList(roundDto));

        Set<ConstraintViolation<PackageDto>> violations = validator.validate(aPackage);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        PackageDto aPackage = new PackageDto();

        Set<ConstraintViolation<PackageDto>> violations = validator.validate(aPackage);

        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("packageId") &&
                                testObjectConstraintViolation.getMessage().equals("packageId cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("rounds") &&
                                testObjectConstraintViolation.getMessage().equals("Rounds cannot be null"));
    }

    @Test
    public void whenAllInvalid() {
        PackageDto aPackage = new PackageDto();
        aPackage.setPackageId(0L);

        Set<ConstraintViolation<PackageDto>> violations = validator.validate(aPackage);

        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("rounds") &&
                                testObjectConstraintViolation.getMessage().equals("Rounds cannot be null"));
        assertThat(violations).anyMatch(
                testObjectConstraintViolation ->
                        testObjectConstraintViolation.getPropertyPath().toString().equals("packageId") &&
                                testObjectConstraintViolation.getMessage().equals("packageId must be greater than 1"));
    }

}