package ru.quest.dto;

import org.hibernate.validator.constraints.Range;
import ru.quest.model.level.LevelTypeSubset;
import ru.quest.model.level.Level;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoundsDto {
    @NotNull(message = "Index cannot be null")
    @Range(min=1, message
            = "Index must be greater than 1")
    private Integer index;
    @NotNull(message = "Questions cannot be null")
    @Range(min=1, message
            = "Questions must be greater than 1")
    private Integer count;
    @NotBlank(message = "Round name not be blank")
    private String name;
    @NotNull(message = "level cannot be null")
    @LevelTypeSubset(anyOf = {Level.EASY, Level.MEDIUM, Level.HARD})

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
