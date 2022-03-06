package com.matheuscordeiro.bffmarvelapi.service.ports;

import com.matheuscordeiro.bffmarvelapi.dto.MarvelCharacter;

import java.util.List;

public interface BffService {
    List<MarvelCharacter> findCharacters(String name);
}
