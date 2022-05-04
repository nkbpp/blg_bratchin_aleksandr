package ru.quest.service;

import org.junit.jupiter.api.Test;
import ru.quest.model.Level;
import ru.quest.model.Quest;
import ru.quest.repository.QuestRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QuestServiceTest {

    QuestRepository repository = new QuestRepository();

    @Test
    void getByIdTest() {
        QuestService questService = new QuestService(repository);

        Quest quests = questService.getById(1L);

        assertThat(quests.getQuest_id()).isEqualTo(1L);
    }

    @Test
    void findByParamsTest() {
        QuestService questService = new QuestService(repository);

        List<Quest> quests = questService.findByParams("Отравил", Level.MEDIUM,List.of(1L,2L));

        assertThat(quests)
                .isNotEmpty();
    }

    @Test
    void getAllTest() {
        QuestService questService = new QuestService(repository);

        List<Quest> quests = questService.getAll();

        assertThat(quests.size()).isEqualTo(22);
    }

}