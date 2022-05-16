package ru.quest.dto;

public class ThemeDto {

    private Long themeId;

    private String theme;

    public ThemeDto() {
    }

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
