package ru.quest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quest.dto.QuestDto;
import ru.quest.dto.RoundsDto;
import ru.quest.mapper.QuestMapper;
import ru.quest.model.Level;
import ru.quest.model.Package;
import ru.quest.model.Quest;
import ru.quest.service.QuestService;
import ru.quest.service.RoundService;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;
    private final RoundService roundService;
    private final QuestMapper questMapper;

    public QuestController(QuestService questService, RoundService roundService, QuestMapper questMapper) {
        this.questService = questService;
        this.roundService = roundService;
        this.questMapper = questMapper;
    }

    /**
     * Удалить вопрос
     */
    @DeleteMapping("/{questId}")
    public ResponseEntity deletteQuest(@PathVariable("questId") long questId) {
        questService.delete(questId);
        return ResponseEntity.ok().build();
    }

    /**
     * Найти вопрос по ID
     */
    @GetMapping(path = "/{questId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Quest getQuestById(@PathVariable("questId") Long questId) {
        return questService.getById(questId);
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
    public ResponseEntity updateQuest(@RequestBody QuestDto questDto) {
        questService.update(questMapper.fromDto(questDto));
        return ResponseEntity.ok().build();
    }

    /**
     * Добавление вопроса
     */
    @PostMapping()
    public ResponseEntity addQuest(@RequestBody QuestDto questDto) {
        questService.save(questMapper.fromDto(questDto));
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
    @GetMapping(value = "/paskage", produces = MediaType.APPLICATION_XML_VALUE)
    public HttpEntity<byte[]> createPaskage(@RequestParam(value = "name") String name,
                                            @RequestParam(value = "author") String author,
                                            @RequestParam(value = "info", required = false) String info,
                                            @RequestBody() List<RoundsDto> roundsDtos) {

        XmlMapper xmlMapper = new XmlMapper();
        String xml;
        try {
            xml = xmlMapper.writeValueAsString(Package.builder()
                    .setName(name)
                    .setAuthor(author)
                    .setInfo(info)
                    .setRounds(roundService.toEntity(roundsDtos))
                    .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("failed to convert to xml");
        }

        byte[] arr = xml.getBytes(StandardCharsets.UTF_8);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_XML);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "xml");
        header.setContentLength(arr.length);
        return new HttpEntity<>(arr, header);
    }

}
