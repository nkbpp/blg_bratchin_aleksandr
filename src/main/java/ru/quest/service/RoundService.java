package ru.quest.service;

import org.springframework.stereotype.Service;
import ru.quest.dto.RoundsDto;
import ru.quest.model.Quest;
import ru.quest.model.Round;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoundService {

    private final QuestService questService;

    public RoundService(QuestService questService) {
        this.questService = questService;
    }

    public List<Round> toEntity(List<RoundsDto> dto) {
        List<Long> ids = new ArrayList<>();
        List<Round> entity = new ArrayList<>();

        for (RoundsDto roundsDto :
                dto) {

            List<Quest> filterQuest = questService.findByParams(null, roundsDto.getLevel(), null)
                    .stream()
                    .filter(quest -> ids
                            .stream()
                            .noneMatch(aLong -> quest.getQuest_id().equals(aLong))
                    )
                    .limit(roundsDto.getCount()).toList();

            entity.add(new Round(roundsDto.getName(),roundsDto.getIndex(),filterQuest,null));

            //индексы вопросов которые уже были добавлены
            for (Quest quest:
                filterQuest) {
                ids.add(quest.getQuest_id());
            }

        }

        return entity;
    }
}
