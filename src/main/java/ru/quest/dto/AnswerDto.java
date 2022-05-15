package ru.quest.dto;

public class AnswerDto {

    private Long answer_id;

    private String answer;

    private Boolean correct_answer;

    public AnswerDto() {
    }

    public Long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(Boolean correct_answer) {
        this.correct_answer = correct_answer;
    }

}
