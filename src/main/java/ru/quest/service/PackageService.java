package ru.quest.service;

import org.springframework.stereotype.Service;

@Service
public class PackageService {

    private final RoundService roundService;

    public PackageService(RoundService roundService) {
        this.roundService = roundService;
    }

}
