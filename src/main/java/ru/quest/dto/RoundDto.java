package ru.quest.dto;

import ru.quest.model.Quest;
import ru.quest.model.Theme;

import java.util.List;

public class RoundDto {

    private Long round_id;

    private String name;

    private Integer index;

    private List<Quest> quests;

    private List<Theme> themes;

    public RoundDto() {
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

}
