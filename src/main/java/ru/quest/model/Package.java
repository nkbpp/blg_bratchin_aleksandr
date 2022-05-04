package ru.quest.model;

import java.util.List;
import java.util.Objects;

public class Package {

    private Long paskage_id;

    private String name;

    private String info;

    private String author;

    private List<Round> rounds;

    public Package(Long paskage_id, String name, String info, String author, List<Round> rounds) {
        this.paskage_id = paskage_id;
        this.name = name;
        this.info = info;
        this.author = author;
        this.rounds = rounds;
    }

    public Package(String name, String info, String author, List<Round> rounds) {
        this.name = name;
        this.info = info;
        this.author = author;
        this.rounds = rounds;
    }

    public Package() {

    }

    public Long getPaskage_id() {
        return paskage_id;
    }

    public void setPaskage_id(Long paskage_id) {
        this.paskage_id = paskage_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return Objects.equals(paskage_id, aPackage.paskage_id) && Objects.equals(name, aPackage.name) && Objects.equals(info, aPackage.info) && Objects.equals(author, aPackage.author) && Objects.equals(rounds, aPackage.rounds);
    }

    @Override
    public int hashCode() {
        return (int)(long) paskage_id;
    }
}
