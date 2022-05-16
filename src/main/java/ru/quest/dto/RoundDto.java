package ru.quest.dto;

import ru.quest.model.Quest;
import ru.quest.model.Theme;

import java.util.List;

public class RoundDto {

    private Long round_id;

    private String name;

    private Integer index;

    private List<QuestDto> quests;

    private List<ThemeDto> themes;

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

    public List<QuestDto> getQuests() {
        return quests;
    }

    public void setQuests(List<QuestDto> quests) {
        this.quests = quests;
    }

    public List<ThemeDto> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeDto> themes) {
        this.themes = themes;
    }
}
