package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.RoundDto;
import ru.quest.model.Round;

@Component
public class RoundMapper {

    public RoundDto toDto(Round round) {
        RoundDto dto = new RoundDto();
        dto.setRound_id(round.getRound_id());
        dto.setName(round.getName());
        dto.setIndex(round.getIndex());
        dto.setQuests(round.getQuests());
        dto.setThemes(round.getThemes());
        return dto;
    }

    public Round fromDto(RoundDto dto) {
        return Round.builder()
                .setRound_id(dto.getRound_id())
                .setName(dto.getName())
                .setIndex(dto.getIndex())
                .setQuests(dto.getQuests())
                .setThemes(dto.getThemes())
                .build();
    }

}
