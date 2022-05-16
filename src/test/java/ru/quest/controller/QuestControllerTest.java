package ru.quest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import ru.quest.dto.QuestDto;
import ru.quest.dto.RoundsDto;
import ru.quest.mapper.QuestMapper;
import ru.quest.model.Level;
import ru.quest.model.Quest;
import ru.quest.model.Round;
import ru.quest.service.QuestService;
import ru.quest.service.RoundService;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
class QuestControllerTest {


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private QuestService questService;

    @Mock
    private RoundService roundService;

    @Spy
    private QuestMapper questMapper;

    @InjectMocks
    QuestController questController;



    QuestDto questDto = new QuestDto();
    Quest quest = new Quest();

    @Test
    void deletteQuest() {
        questController.deletteQuest(1L);

        Mockito.verify(questService).delete(1L);
    }

    @Test
    void getQuestById() {
        Mockito.when(questService.getById(1L)).thenReturn(new Quest());

        questController.getQuestById(1L);

        Mockito.verify(questService).getById(1L);
    }

    @Test
    void getAll() {
        Mockito.when(questService.getAll()).thenReturn(List.of());

        questController.getAll();

        Mockito.verify(questService).getAll();
    }

    @Test
    void updateQuest() {
        questController.updateQuest(questDto);

        Mockito.verify(questService).update(quest);
    }

    @Test
    void addQuest() {
        questController.addQuest(questDto);

        Mockito.verify(questService).save(quest);
    }

    @Test
    void findByParams() {
        Mockito.when(questService.findByParams("Ответ", Level.MEDIUM,List.of(1L))).thenReturn(List.of(new Quest()));

        questController.findByParams("Ответ", Level.MEDIUM,List.of(1L));

        Mockito.verify(questService).findByParams("Ответ", Level.MEDIUM,List.of(1L));
    }

    @Test
    void createPackage() {
        var roundsDtos = List.of(new RoundsDto());
        Mockito.when(roundService.toEntity(roundsDtos)).thenReturn(List.of(new Round()));

        questController.createPackage("","","", roundsDtos);

        Mockito.verify(roundService).toEntity(roundsDtos);
    }


}