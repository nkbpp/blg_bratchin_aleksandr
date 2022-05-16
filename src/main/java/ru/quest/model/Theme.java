package ru.quest.model;

import java.util.Objects;

public class Theme {

    private Long theme_id;

    private String theme;

    public Theme() {
    }

    public Theme(Long theme_id, String theme) {
        this.theme_id = theme_id;
        this.theme = theme;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme1 = (Theme) o;
        return Objects.equals(theme_id, theme1.theme_id) && Objects.equals(theme, theme1.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme_id, theme);
    }
}
