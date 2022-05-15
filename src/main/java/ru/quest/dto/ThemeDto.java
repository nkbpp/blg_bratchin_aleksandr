package ru.quest.dto;

public class ThemeDto {

    private Long theme_id;

    private String theme;

    public ThemeDto() {
    }

    public Long getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Long theme_id) {
        this.theme_id = theme_id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
