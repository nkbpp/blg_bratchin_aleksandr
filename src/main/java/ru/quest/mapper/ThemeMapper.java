package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.ThemeDto;
import ru.quest.model.Theme;

@Component
public class ThemeMapper {

    public ThemeDto toDto(Theme theme) {
        ThemeDto dto = new ThemeDto();
        dto.setTheme_id(theme.getTheme_id());
        dto.setTheme(theme.getTheme());
        return dto;
    }

    public Theme fromDto(ThemeDto dto) {
        Theme theme = new Theme();
        theme.setTheme_id(theme.getTheme_id());
        theme.setTheme(theme.getTheme());
        return theme;
    }

}
