package ru.quest.mapper;

import org.springframework.stereotype.Component;
import ru.quest.dto.PackageDto;
import ru.quest.model.Package;

import java.util.stream.Collectors;

@Component
public class PackageMapper {

    private final RoundMapper roundMapper;

    public PackageMapper(RoundMapper roundMapper) {
        this.roundMapper = roundMapper;
    }

    public PackageDto toDto(Package aPackage) {
        PackageDto dto = new PackageDto();
        dto.setPackage_id(aPackage.getPackage_id());
        dto.setName(aPackage.getName());
        dto.setAuthor(aPackage.getAuthor());
        dto.setInfo(aPackage.getInfo());

        dto.setRounds(aPackage.getRounds().stream()
                .map(round -> roundMapper.toDto(round))
                .collect(Collectors.toList()));

        return dto;
    }

    public Package fromDto(PackageDto dto) {
        return Package.builder()
                .setPackage_id(dto.getPackage_id())
                .setName(dto.getName())
                .setAuthor(dto.getAuthor())
                .setInfo(dto.getInfo())
                .setRounds(dto.getRounds().stream()
                        .map(round -> roundMapper.fromDto(round))
                        .collect(Collectors.toList()))
                .build();
    }

}
