package ru.quest.dto;

import org.hibernate.validator.constraints.Range;
import ru.quest.dto.quest.QuestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class RoundDto {
    @NotNull(message = "roundId cannot be null")
    @Range(min=1, message
            = "roundId must be greater than 1")
    private Long roundId;
    @NotBlank(message = "Name round cannot be blank")
    private String name;
    @NotNull(message = "index cannot be null")
    @Range(min=1, message
            = "index must be greater than 1")
    private Integer index;

    @Size(min = 1, message = "Quests list cannot be empty")
    @NotNull(message = "quests cannot be null")
    private List<@Valid QuestDto> quests;

    private List<@Valid ThemeDto> themes;

    public RoundDto() {
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
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
