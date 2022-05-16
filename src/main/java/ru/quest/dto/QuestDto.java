package ru.quest.dto;

import ru.quest.model.Level;

import java.util.List;
import java.util.Objects;

public class QuestDto {
    private Long questId;

    private String text_quest;

    private String comment;

    private Level level;

    private List<AnswerDto> answers;

    private List<LinkDto> links;

    private List<ThemeDto> themes;

    public QuestDto() {
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public String getText_quest() {
        return text_quest;
    }

    public void setText_quest(String text_quest) {
        this.text_quest = text_quest;
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
        return Objects.equals(questId, questDto.questId) && Objects.equals(text_quest, questDto.text_quest) && Objects.equals(comment, questDto.comment) && level == questDto.level && Objects.equals(answers, questDto.answers) && Objects.equals(links, questDto.links) && Objects.equals(themes, questDto.themes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questId, text_quest, comment, level, answers, links, themes);
    }
}
