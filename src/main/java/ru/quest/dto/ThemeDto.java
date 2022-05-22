package ru.quest.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThemeDto {
    @NotNull(message = "themeId cannot be null")
    @Range(min=1, message
            = "themeId must be greater than 1")
    private Long themeId;
    @NotBlank(message = "Theme cannot be blank")
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
