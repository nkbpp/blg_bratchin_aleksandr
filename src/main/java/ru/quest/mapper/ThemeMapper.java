package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.ThemeDto;
import ru.quest.model.Theme;

@Component
public class ThemeMapper {

    public ThemeDto toDto(Theme theme) {
        ThemeDto dto = new ThemeDto();
        dto.setThemeId(theme.getThemeId());
        dto.setTheme(theme.getTheme());
        return dto;
    }

    public Theme fromDto(ThemeDto dto) {
        Theme theme = new Theme();
        theme.setThemeId(theme.getThemeId());
        theme.setTheme(theme.getTheme());
        return theme;
    }

}
