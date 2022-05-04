package ru.quest.model;

import java.util.List;
import java.util.Objects;

public class Round {

    private Long round_id;

    private String name;

    private Integer index;

    private List<Quest> quests;

    private List<Theme> themes;

    public Round() {
    }

    public Round(Long round_id, String name, Integer index, List<Quest> quests, List<Theme> themes) {
        this.round_id = round_id;
        this.name = name;
        this.index = index;
        this.quests = quests;
        this.themes = themes;
    }

    public Round(String name, Integer index, List<Quest> quests, List<Theme> themes) {
        this.name = name;
        this.index = index;
        this.quests = quests;
        this.themes = themes;
    }

    public Long getRound_id() {
        return round_id;
    }

    public void setRound_id(Long round_id) {
        this.round_id = round_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
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
        Round round = (Round) o;
        return Objects.equals(round_id, round.round_id) && Objects.equals(name, round.name) && Objects.equals(index, round.index) && Objects.equals(quests, round.quests) && Objects.equals(themes, round.themes);
    }

    @Override
    public int hashCode() {
        return (int)(long)round_id;
    }
}
