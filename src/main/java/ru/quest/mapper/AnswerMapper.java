package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.AnswerDto;
import ru.quest.model.Answer;

@Component
public class AnswerMapper {

    public AnswerDto toDto(Answer answer) {
        AnswerDto dto = new AnswerDto();
        dto.setAnswerId(answer.getAnswerId());
        dto.setAnswer(answer.getAnswer());
        dto.setCorrect_answer(answer.getCorrect_answer());
        return dto;
    }

    public Answer fromDto(AnswerDto dto) {
        return Answer.builder()
                .setAnswer_id(dto.getAnswerId())
                .setAnswer(dto.getAnswer())
                .setCorrect_answer(dto.getCorrect_answer())
                .build();
    }

}
