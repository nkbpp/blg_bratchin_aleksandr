package ru.quest.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Package {
    @NotNull(message = "packageId cannot be null")
    @Range(min=1, message
            = "packageId must be greater than 1")
    private Long packageId;

    private String name;

    private String info;

    private String author;
    @Size(min = 1, message = "Rounds list cannot be empty")
    @NotNull(message = "Rounds cannot be null")
    private List<Round> rounds;

    public Package(Long packageId, String name, String info, String author, List<Round> rounds) {
        this.packageId = packageId;
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

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
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
        return Objects.equals(packageId, aPackage.packageId) && Objects.equals(name, aPackage.name) && Objects.equals(info, aPackage.info) && Objects.equals(author, aPackage.author) && Objects.equals(rounds, aPackage.rounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, name, info, author, rounds);
    }

    public static Builder builder() {
        return new Package().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setPackage_id(Long paskage_id) {
            Package.this.packageId = paskage_id;
            return this;
        }
        public Builder setName(String name) {
            Package.this.name = name;
            return this;
        }
        public Builder setInfo(String info) {
            Package.this.info = info;
            return this;
        }
        public Builder setAuthor(String author) {
            Package.this.author = author;
            return this;
        }
        public Builder setRounds(List<Round> rounds) {
            Package.this.rounds = rounds;
            return this;
        }

        public Package build() {
            return Package.this;
        }

    }

}
