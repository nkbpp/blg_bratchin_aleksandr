package ru.quest.repository;

import ru.quest.model.Quest;

public class QuestSpecification implements Specification<Quest>{

    private final Long id;

    public QuestSpecification(Long id) {
        this.id = id;
    }


    public QuestSpecification(Quest quest) {
        this.id = quest.getQuest_id();
    }

    @Override
    public boolean specified(Quest quest) {
        return id.equals(quest.getQuest_id());
    }

}
