package ru.quest.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class PackageDto {
    @NotNull(message = "packageId cannot be null")
    @Range(min=1, message
            = "packageId must be greater than 1")
    private Long packageId;

    private String name;

    private String info;

    private String author;
    @Size(min = 1, message = "Rounds list cannot be empty")
    @NotNull(message = "Rounds cannot be null")
    private List<@Valid RoundDto> rounds;

    public PackageDto() {
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

    public List<RoundDto> getRounds() {
        return rounds;
    }

    public void setRounds(List<RoundDto> rounds) {
        this.rounds = rounds;
    }

}
