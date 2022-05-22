package ru.quest.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Link {
    @NotNull(message = "linkId cannot be null")
    @Range(min=1, message
            = "linkId must be greater than 1")
    private Long linkId;
    @NotBlank(message = "Link cannot be blank")
    private String link;

    public Link() {
    }

    public Link(Long linkId, String link) {
        this.linkId = linkId;
        this.link = link;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link1 = (Link) o;
        return Objects.equals(linkId, link1.linkId) && Objects.equals(link, link1.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkId, link);
    }
}
