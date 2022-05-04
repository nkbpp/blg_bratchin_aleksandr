package ru.quest.model;

import java.util.Objects;

public class Answer {

    private Long answer_id;

    private String answer;

    private Boolean correct_answer;


    public Answer() {
    }

    public Answer(Long answer_id, String answer, Boolean correct_answer) {
        this.answer_id = answer_id;
        this.answer = answer;
        this.correct_answer = correct_answer;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(answer_id, answer1.answer_id) && Objects.equals(answer, answer1.answer) && Objects.equals(correct_answer, answer1.correct_answer);
    }

    @Override
    public int hashCode() {
        return (int)(long)answer_id;
    }
}
