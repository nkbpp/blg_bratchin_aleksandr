package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.QuestDto;
import ru.quest.model.Quest;

import java.util.stream.Collectors;

@Component
public class QuestMapper {


    private final AnswerMapper answerMapper;
    private final LinkMapper linkMapper;
    private final ThemeMapper themeMapper;

    public QuestMapper(AnswerMapper answerMapper, LinkMapper linkMapper, ThemeMapper themeMapper) {
        this.answerMapper = answerMapper;
        this.linkMapper = linkMapper;
        this.themeMapper = themeMapper;
    }

    public QuestDto toDto(Quest quest) {
        QuestDto dto = new QuestDto();
        dto.setQuest_id(quest.getQuestId());
        dto.setText_quest(quest.getTextQuest());
        dto.setComment(quest.getComment());
        dto.setLevel(quest.getLevel());

        dto.setAnswers(quest.getAnswers().stream()
                .map(answer -> answerMapper.toDto(answer))
                .collect(Collectors.toList()));

        dto.setLinks(quest.getLinks().stream()
                .map(link -> linkMapper.toDto(link))
                .collect(Collectors.toList()));

        dto.setThemes(quest.getThemes().stream()
                .map(theme -> themeMapper.toDto(theme))
                .collect(Collectors.toList()));

        return dto;
    }

    public Quest fromDto(QuestDto dto) {
        return Quest.builder()
                .setQuestId(dto.getQuest_id())
                .setTextQuest(dto.getText_quest())
                .setComment(dto.getComment())
                .setLevel(dto.getLevel())
                .setAnswers(dto.getAnswers().stream()
                        .map(answer -> answerMapper.fromDto(answer))
                        .collect(Collectors.toList()))
                .setLinks(dto.getLinks().stream()
                        .map(link -> linkMapper.fromDto(link))
                        .collect(Collectors.toList()))
                .setThemes(dto.getThemes().stream()
                        .map(theme -> themeMapper.fromDto(theme))
                        .collect(Collectors.toList()))
                .build();
    }


}
