package ru.quest.dto.quest;

import ru.quest.dto.AnswerDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CorrectAnswerDtoExistsValidator implements ConstraintValidator<CorrectAnswerDtoExists, List<AnswerDto>> {

    @Override
    public boolean isValid(List<AnswerDto> answerDtos, ConstraintValidatorContext constraintValidatorContext) {
        return answerDtos != null && answerDtos.size() >= 1 && answerDtos.stream().anyMatch(
                AnswerDto::getCorrectAnswer
        );
    }
}
