package ru.quest.dto;

import ru.quest.model.Answer;
import ru.quest.model.Level;
import ru.quest.model.Link;
import ru.quest.model.Theme;

import java.util.List;
import java.util.Objects;

public class QuestDto {
    private Long quest_id;

    private String text_quest;

    private String comment;

    private Level level;

    private List<Answer> answers;

    private List<Link> links;

    private List<Theme> themes;

    public QuestDto() {
    }

    public Long getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(Long quest_id) {
        this.quest_id = quest_id;
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
        QuestDto questDto = (QuestDto) o;
        return Objects.equals(quest_id, questDto.quest_id) && Objects.equals(text_quest, questDto.text_quest) && Objects.equals(comment, questDto.comment) && level == questDto.level && Objects.equals(answers, questDto.answers) && Objects.equals(links, questDto.links) && Objects.equals(themes, questDto.themes);
    }

    @Override
    public int hashCode() {
        return (int) (long) quest_id;
    }
}
