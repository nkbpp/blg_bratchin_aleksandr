package ru.quest.model;

import org.hibernate.validator.constraints.Range;
import ru.quest.model.quest.Quest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Round {
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
    private List<@Valid Quest> quests;

    private List<@Valid Theme> themes;

    public Round() {
    }

    public Round(Long roundId, String name, Integer index, List<Quest> quests, List<Theme> themes) {
        this.roundId = roundId;
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
        return Objects.equals(roundId, round.roundId) && Objects.equals(name, round.name) && Objects.equals(index, round.index) && Objects.equals(quests, round.quests) && Objects.equals(themes, round.themes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, name, index, quests, themes);
    }

    public static Builder builder() {
        return new Round().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setRound_id(Long round_id) {
            Round.this.roundId = round_id;
            return this;
        }
        public Builder setName(String name) {
            Round.this.name = name;
            return this;
        }
        public Builder setIndex(Integer index) {
            Round.this.index = index;
            return this;
        }
        public Builder setQuests(List<Quest> quests) {
            Round.this.quests = quests;
            return this;
        }
        public Builder setThemes(List<Theme> themes) {
            Round.this.themes = themes;
            return this;
        }

        public Round build() {
            return Round.this;
        }

    }

}
