package ru.quest.dto;

import ru.quest.model.Level;

public class RoundsDto {

    private Integer index;

    private Integer count;

    private String name;

    private Level level;

    public RoundsDto(Integer index, Integer count, String name, Level level) {
        this.index = index;
        this.count = count;
        this.name = name;
        this.level = level;
    }

    public RoundsDto() {
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
