package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.LinkDto;
import ru.quest.model.Link;

@Component
public class LinkMapper {

    public LinkDto toDto(Link link) {
        LinkDto dto = new LinkDto();
        dto.setLinkId(link.getLinkId());
        dto.setLink(link.getLink());
        return dto;
    }

    public Link fromDto(LinkDto dto) {
        Link link = new Link();
        link.setLinkId(link.getLinkId());
        link.setLink(link.getLink());
        return link;
    }

}
