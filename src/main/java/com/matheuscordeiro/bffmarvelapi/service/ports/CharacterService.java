package com.matheuscordeiro.bffmarvelapi.service.ports;

import com.matheuscordeiro.bffmarvelapi.dto.MarvelCharacter;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelComics;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelEvents;

import java.util.List;

public interface CharacterService {
    List<MarvelCharacter> findCharacters(String name);

    List<MarvelComics> findComicsByCharacter(String id);

    List<MarvelEvents> findEventsByCharacter(String id);
}
