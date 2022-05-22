package ru.quest.service.quest;

import org.springframework.stereotype.Service;
import ru.quest.exeptions.NotFoundException;
import ru.quest.model.level.Level;
import ru.quest.model.quest.Quest;
import ru.quest.repository.quest.QuestRepository;
import ru.quest.repository.quest.QuestSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public Quest getById(Long id){
        return questRepository.query(new QuestSpecification(id))
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Object with index " + id + " not found in class Quest"));
    }

    public List<Quest> findByParams(String answer, Level level, List<Long> themeIds) {

        return getAll()
                .stream()
                .filter(quest -> answer == null ||
                        quest.getAnswers()
                                .stream()
                                .anyMatch(answer1 ->  answer1.getAnswer().equals(answer) && answer1.getCorrectAnswer())
                )
                .filter(quest -> level == null || level.equals(quest.getLevel()))
                .filter(quest -> themeIds == null ||
                        quest.getThemes()
                                .stream()
                                .anyMatch(
                                        theme -> themeIds.stream()
                                                .anyMatch(aLong -> aLong.equals(theme.getThemeId()))
                                )
                )
                .collect(Collectors.toList());
    }

    public List<Quest> getAll(){
        return questRepository.query(o -> true);
    }

    public void delete(Long q){
        questRepository.remove(getById(q));
    }

    public void save(Quest quest) {
        questRepository.add(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }






}
