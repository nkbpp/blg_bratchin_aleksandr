package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.PackageDto;
import ru.quest.model.Package;

@Component
public class PackageMapper {

    public PackageDto toDto(Package aPackage) {
        PackageDto dto = new PackageDto();
        dto.setPackage_id(aPackage.getPackage_id());
        dto.setName(aPackage.getName());
        dto.setAuthor(aPackage.getAuthor());
        dto.setInfo(aPackage.getInfo());
        dto.setRounds(aPackage.getRounds());

        return dto;
    }

    public Package fromDto(PackageDto dto) {
        return Package.builder()
                .setPackage_id(dto.getPackage_id())
                .setName(dto.getName())
                .setAuthor(dto.getAuthor())
                .setInfo(dto.getInfo())
                .setRounds(dto.getRounds())
                .build();
    }

}
