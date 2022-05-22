package ru.quest.model.quest;

import ru.quest.model.Answer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CorrectAnswerExistsValidator implements ConstraintValidator<CorrectAnswerExists, List<Answer>> {

    @Override
    public boolean isValid(List<Answer> answer, ConstraintValidatorContext constraintValidatorContext) {
        return answer != null && answer.size() >= 1 && answer.stream().anyMatch(
                Answer::getCorrectAnswer
        );
    }
}
