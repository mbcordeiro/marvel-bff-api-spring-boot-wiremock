package com.matheuscordeiro.bffmarvelapi.service;

import com.matheuscordeiro.bffmarvelapi.client.ports.MarvelClient;
import com.matheuscordeiro.bffmarvelapi.client.dto.InlineResponse200;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelCharacter;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelComics;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelEvents;
import com.matheuscordeiro.bffmarvelapi.service.ports.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final MarvelClient client;

    @Override
    public List<MarvelCharacter> findCharacters(String name) {
        final var marvelCharacters = new ArrayList<>();
        InlineResponse200 listCharacters = client.listCharacters(name, null, null, null, null, null,
                null, null, null, null);
        return null;
    }

    @Override
    public List<MarvelComics> findComicsByCharacter(String id) {
        return null;
    }

    @Override
    public List<MarvelEvents> findEventsByCharacter(String id) {
        return null;
    }
}
