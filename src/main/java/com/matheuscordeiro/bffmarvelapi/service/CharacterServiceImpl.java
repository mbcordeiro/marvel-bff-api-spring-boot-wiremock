package com.matheuscordeiro.bffmarvelapi.service;

import com.matheuscordeiro.bffmarvelapi.client.dto.Character;
import com.matheuscordeiro.bffmarvelapi.client.ports.MarvelClient;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelCharacter;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelComics;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelEvents;
import com.matheuscordeiro.bffmarvelapi.file.ports.FileService;
import com.matheuscordeiro.bffmarvelapi.service.ports.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final MarvelClient client;

    private final FileService fileService;

    @Async
    private void getCharacterImage(Character character, String size) {
        try {
            final var response = client.image(new URI(String.format("%s/%s.jpg", character.getThumbnail().getPath(), size)));
            fileService.saveFile(response.getInputStream(),
                    String.format("%s_%s_%s.%s", character.getName().toUpperCase(), character.getId(), size,
                            character.getThumbnail().getExtension()));
        }  catch (URISyntaxException | IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public List<MarvelCharacter> findCharacters(String name) {
        final var marvelCharacters = new ArrayList<>();
        final var listCharacters = client.listCharacters(name, null, null, null, null, null,
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
