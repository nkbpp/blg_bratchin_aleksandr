package ru.quest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quest.dto.RoundsDto;
import ru.quest.model.*;
import ru.quest.model.Package;
import ru.quest.service.QuestService;
import ru.quest.service.RoundService;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;
    private final RoundService roundService;

    public QuestController(QuestService questService, RoundService roundService) {
        this.questService = questService;
        this.roundService = roundService;
    }

    /**
     * Удалить вопрос
     */
    @DeleteMapping("/{questID}")
    public ResponseEntity deletteQuest(@PathVariable("questID") long questID) {
        questService.delete(questID);
        return ResponseEntity.ok().build();
    }

    /**
     * Найти вопрос по ID
     */
    @GetMapping(path = "/{questID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Quest getQuestById(@PathVariable("questID") Long questID) {
        return questService.getById(questID);
    }

    /**
     * Получить все вопросы
     */
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Quest> getAll() {
        return questService.getAll();
    }

    /**
     * Обновить существующий вопрос
     */
    @PutMapping()
    public ResponseEntity updateQuest(@RequestBody Quest quest) {
        questService.update(quest);
        return ResponseEntity.ok().build();
    }

    /**
     * Добавление вопроса
     */
    @PostMapping()
    public ResponseEntity addQuest(@RequestBody Quest quest) {
        questService.save(quest);
        return ResponseEntity.ok().build();
    }

    /**
     * Поиск вопроса по параметрам
     */
    @GetMapping(path = "/findByParams", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Quest> findByParams(@RequestParam(value = "answer", required = false) String answer,
                                    @RequestParam(value = "level", required = false) Level level,
                                    @RequestParam(value = "themeIds", required = false) List<Long> themeIds) {

        return questService.findByParams(answer, level, themeIds);
    }

    /**
     * Получить пакет по параметрам
     */
    @GetMapping(value = "/package", produces = MediaType.APPLICATION_XML_VALUE)
    public HttpEntity<byte[]> createPaskage(@RequestParam(value = "name") String name,
                                            @RequestParam(value = "author") String author,
                                            @RequestParam(value = "info", required = false) String info,
                                            @RequestBody() List<RoundsDto> roundsDtos) {

        XmlMapper xmlMapper = new XmlMapper();
        String xml;
        try {
            xml = xmlMapper.writeValueAsString(new Package(name, author, info, roundService.toEntity(roundsDtos)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("failed to convert to xml");
        }

        byte[] arr = xml.getBytes(StandardCharsets.UTF_8);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_XML);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "xml");
        header.setContentLength(arr.length);
        return new HttpEntity<>(arr, header);

        //return new Package(name, author, info, roundService.toEntity(roundsDtos));
    }

}
