package ru.quest.model.level;

public enum Level {
    EASY("Простой"),
    MEDIUM("Средний"),
    HARD("Сложный");

    private String level;

    Level(String level){
        this.level = level;
    }

    public String getLevel(){ return level;}
}
