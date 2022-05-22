package ru.quest.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnswerDto {

    @NotNull(message = "answerId cannot be null")
    @Range(min=1, message
            = "answerId must be greater than 1")
    private Long answerId;
    @NotBlank(message = "Answer cannot be blank")
    private String answer;
    @NotNull(message = "Must be set")
    private Boolean correctAnswer;

    public AnswerDto() {
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
