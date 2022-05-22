package ru.quest.dto.quest;

import org.hibernate.validator.constraints.Range;
import ru.quest.dto.AnswerDto;
import ru.quest.dto.LinkDto;
import ru.quest.dto.ThemeDto;
import ru.quest.model.level.Level;
import ru.quest.model.level.LevelTypeSubset;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class QuestDto {
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
    @CorrectAnswerDtoExists
    private List<@Valid AnswerDto> answers;

    private List<@Valid LinkDto> links;

    private List<@Valid ThemeDto> themes;

    public QuestDto() {
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

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public List<LinkDto> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDto> links) {
        this.links = links;
    }

    public List<ThemeDto> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeDto> themes) {
        this.themes = themes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestDto questDto = (QuestDto) o;
        return Objects.equals(questId, questDto.questId) && Objects.equals(textQuest, questDto.textQuest) && Objects.equals(comment, questDto.comment) && level == questDto.level && Objects.equals(answers, questDto.answers) && Objects.equals(links, questDto.links) && Objects.equals(themes, questDto.themes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questId, textQuest, comment, level, answers, links, themes);
    }

}
