package ru.quest.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Answer {
    @NotNull(message = "answerId cannot be null")
    @Range(min=1, message
            = "answerId must be greater than 1")
    private Long answerId;
    @NotBlank(message = "Answer cannot be blank")
    private String answer;
    @NotNull(message = "Must be set")
    private Boolean correctAnswer;


    public Answer() {
    }

    public Answer(Long answerId, String answer, Boolean correctAnswer) {
        this.answerId = answerId;
        this.answer = answer;
        this.correctAnswer = correctAnswer;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(answerId, answer1.answerId) && Objects.equals(answer, answer1.answer) && Objects.equals(correctAnswer, answer1.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, answer, correctAnswer);
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
            Answer.this.correctAnswer = correct_answer;
            return this;
        }

        public Answer build() {
            return Answer.this;
        }

    }

}
