package ru.quest.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Theme {
    @NotNull(message = "themeId cannot be null")
    @Range(min=1, message
            = "themeId must be greater than 1")
    private Long themeId;
    @NotBlank(message = "Theme cannot be blank")
    private String theme;

    public Theme() {
    }

    public Theme(Long themeId, String theme) {
        this.themeId = themeId;
        this.theme = theme;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme1 = (Theme) o;
        return Objects.equals(themeId, theme1.themeId) && Objects.equals(theme, theme1.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(themeId, theme);
    }
}
