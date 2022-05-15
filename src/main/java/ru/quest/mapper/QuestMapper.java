package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.QuestDto;
import ru.quest.model.Quest;

@Component
public class QuestMapper {

    public QuestDto toDto(Quest quest) {
        QuestDto dto = new QuestDto();
        dto.setQuest_id(quest.getQuestId());
        dto.setText_quest(quest.getTextQuest());
        dto.setComment(quest.getComment());
        dto.setLevel(quest.getLevel());
        dto.setAnswers(quest.getAnswers());
        dto.setLinks(quest.getLinks());
        dto.setThemes(quest.getThemes());
        return dto;
    }

    public Quest fromDto(QuestDto dto) {
        return Quest.builder()
                .setQuestId(dto.getQuest_id())
                .setTextQuest(dto.getText_quest())
                .setComment(dto.getComment())
                .setLevel(dto.getLevel())
                .setAnswers(dto.getAnswers())
                .setLinks(dto.getLinks())
                .setThemes(dto.getThemes())
                .build();
    }


}
