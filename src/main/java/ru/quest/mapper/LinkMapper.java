package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.LinkDto;
import ru.quest.model.Link;

@Component
public class LinkMapper {

    public LinkDto toDto(Link link) {
        LinkDto dto = new LinkDto();
        dto.setLink_id(link.getLink_id());
        dto.setLink(link.getLink());
        return dto;
    }

    public Link fromDto(LinkDto dto) {
        Link link = new Link();
        link.setLink_id(link.getLink_id());
        link.setLink(link.getLink());
        return link;
    }

}
