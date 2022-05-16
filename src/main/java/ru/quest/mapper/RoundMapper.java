package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.RoundDto;
import ru.quest.model.Round;

import java.util.stream.Collectors;

@Component
public class RoundMapper {

    private final QuestMapper questMapper;
    private final ThemeMapper themeMapper;

    public RoundMapper(QuestMapper questMapper, ThemeMapper themeMapper) {
        this.questMapper = questMapper;
        this.themeMapper = themeMapper;
    }

    public RoundDto toDto(Round round) {
        RoundDto dto = new RoundDto();
        dto.setRound_id(round.getRound_id());
        dto.setName(round.getName());
        dto.setIndex(round.getIndex());

        dto.setQuests(round.getQuests().stream()
                .map(quest -> questMapper.toDto(quest))
                .collect(Collectors.toList()));

        dto.setThemes(round.getThemes().stream()
                .map(theme -> themeMapper.toDto(theme))
                .collect(Collectors.toList()));

        return dto;
    }

    public Round fromDto(RoundDto dto) {
        return Round.builder()
                .setRound_id(dto.getRound_id())
                .setName(dto.getName())
                .setIndex(dto.getIndex())
                .setQuests(dto.getQuests().stream()
                        .map(quest -> questMapper.fromDto(quest))
                        .collect(Collectors.toList()))
                .setThemes(dto.getThemes().stream()
                        .map(theme -> themeMapper.fromDto(theme))
                        .collect(Collectors.toList()))
                .build();
    }

}
