package ru.quest.model;

import java.util.Objects;

public class Answer {

    private Long answerId;

    private String answer;

    private Boolean correct_answer;


    public Answer() {
    }

    public Answer(Long answerId, String answer, Boolean correct_answer) {
        this.answerId = answerId;
        this.answer = answer;
        this.correct_answer = correct_answer;

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
        return Objects.equals(answerId, answer1.answerId) && Objects.equals(answer, answer1.answer) && Objects.equals(correct_answer, answer1.correct_answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, answer, correct_answer);
    }

    public static Builder builder() {
        return new Answer().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setAnswer_id(Long answer_id) {
            Answer.this.answerId = answer_id;
            return this;
        }

        public Builder setAnswer(String answer) {
            Answer.this.answer = answer;
            return this;
        }

        public Builder setCorrect_answer(Boolean correct_answer) {
            Answer.this.correct_answer = correct_answer;
            return this;
        }

        public Answer build() {
            return Answer.this;
        }

    }

}
