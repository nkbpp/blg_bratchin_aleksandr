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

class PackageTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    public void whenAllAcceptable() {
        Package aPackage = new Package();
        aPackage.setPackageId(1L);
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
        aPackage.setRounds(asList(round));

        Set<ConstraintViolation<Package>> violations = validator.validate(aPackage);

        assertThat(violations).isEmpty();
    }

    @Test
    public void whenAllNull() {
        Package aPackage = new Package();

        Set<ConstraintViolation<Package>> violations = validator.validate(aPackage);

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
        Package aPackage = new Package();
        aPackage.setPackageId(0L);

        Set<ConstraintViolation<Package>> violations = validator.validate(aPackage);

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