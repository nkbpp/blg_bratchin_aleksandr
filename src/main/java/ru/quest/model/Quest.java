package ru.quest.model;

import java.util.List;
import java.util.Objects;

public class Quest {

    private Long quest_id;

    private String text_quest;

    private String comment;

    private Level level;

    private List<Answer> answers;

    private List<Link> links;

    private List<Theme> themes;

    public Quest() {
    }

    public Quest(Long quest_id, String text_quest, String comment, Level level, List<Answer> answers, List<Link> links, List<Theme> themes) {
        this.quest_id = quest_id;
        this.text_quest = text_quest;
        this.comment = comment;
        this.level = level;
        this.answers = answers;
        this.links = links;
        this.themes = themes;
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
        Quest quest = (Quest) o;
        return Objects.equals(quest_id, quest.quest_id) && Objects.equals(text_quest, quest.text_quest) && Objects.equals(comment, quest.comment) && level == quest.level && Objects.equals(answers, quest.answers) && Objects.equals(links, quest.links) && Objects.equals(themes, quest.themes);
    }

    @Override
    public int hashCode() {
        return (int)(long) quest_id;
    }

    public void setQuest(Quest quest) {
        this.setQuest_id(quest.getQuest_id());
        this.setText_quest(quest.getText_quest());
        this.setComment(quest.getComment());
        this.setLevel(quest.getLevel());
        this.setAnswers(quest.getAnswers());
        this.setLinks(quest.getLinks());
        this.setThemes(quest.getThemes());
    }
}
