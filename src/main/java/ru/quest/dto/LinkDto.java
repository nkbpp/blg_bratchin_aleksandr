package ru.quest.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LinkDto {
    @NotNull(message = "linkId cannot be null")
    @Range(min=1, message
            = "linkId must be greater than 1")
    private Long linkId;
    @NotBlank(message = "Link cannot be blank")
    private String link;

    public LinkDto() {
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
