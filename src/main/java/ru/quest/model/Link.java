package ru.quest.model;

import java.util.Objects;

public class Link {

    private Long link_id;

    private String link;

    public Link() {
    }

    public Link(Long link_id, String link) {
        this.link_id = link_id;
        this.link = link;
    }

    public Long getLink_id() {
        return link_id;
    }

    public void setLink_id(Long link_id) {
        this.link_id = link_id;
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
        return Objects.equals(link_id, link1.link_id) && Objects.equals(link, link1.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link_id, link);
    }
}
