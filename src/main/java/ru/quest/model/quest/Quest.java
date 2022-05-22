package ru.quest.model.quest;

import org.hibernate.validator.constraints.Range;
import ru.quest.dto.quest.CorrectAnswerDtoExists;
import ru.quest.model.Answer;
import ru.quest.model.Link;
import ru.quest.model.Theme;
import ru.quest.model.level.Level;
import ru.quest.model.level.LevelTypeSubset;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Quest {
    @NotNull(message = "questId cannot be null")
    @Range(min=1, message
            = "questId must be greater than 1")
    private Long questId;
    @NotBlank(message = "Text quest cannot be blank")
    private String textQuest;

    private String comment;
    @NotNull(message = "level cannot be null")
    @LevelTypeSubset(anyOf = {Level.EASY, Level.MEDIUM, Level.HARD})
    private Level level;
    @Size(min = 1, message = "Answers list cannot be empty.")
    @NotNull(message = "answers cannot be null")
    @CorrectAnswerExists
    private List<@Valid Answer> answers;

    private List<@Valid Link> links;

    private List<@Valid Theme> themes;

    public Quest() {
    }

    public Quest(Long questId, String textQuest, String comment, Level level, List<Answer> answers, List<Link> links, List<Theme> themes) {
        this.questId = questId;
        this.textQuest = textQuest;
        this.comment = comment;
        this.level = level;
        this.answers = answers;
        this.links = links;
        this.themes = themes;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public String getTextQuest() {
        return textQuest;
    }

    public void setTextQuest(String textQuest) {
        this.textQuest = textQuest;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return Objects.equals(questId, quest.questId) && Objects.equals(textQuest, quest.textQuest) && Objects.equals(comment, quest.comment) && level == quest.level && Objects.equals(answers, quest.answers) && Objects.equals(links, quest.links) && Objects.equals(themes, quest.themes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questId, textQuest, comment, level, answers, links, themes);
    }

    public void setQuest(Quest quest) {
        this.setQuestId(quest.getQuestId());
        this.setTextQuest(quest.getTextQuest());
        this.setComment(quest.getComment());
        this.setLevel(quest.getLevel());
        this.setAnswers(quest.getAnswers());
        this.setLinks(quest.getLinks());
        this.setThemes(quest.getThemes());
    }

    public static Builder builder() {
        return new Quest().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setQuestId(Long questId) {
            Quest.this.questId = questId;
            return this;
        }

        public Builder setTextQuest(String textQuest) {
            Quest.this.textQuest = textQuest;
            return this;
        }

        public Builder setComment(String comment) {
            Quest.this.comment = comment;
            return this;
        }

        public Builder setLevel(Level level) {
            Quest.this.level = level;
            return this;
        }

        public Builder setAnswers(List<Answer> answers) {
            Quest.this.answers = answers;
            return this;
        }

        public Builder setLinks(List<Link> links) {
            Quest.this.links = links;
            return this;
        }

        public Builder setThemes(List<Theme> themes) {
            Quest.this.themes = themes;
            return this;
        }

        public Quest build() {
            return Quest.this;
        }

    }

}
