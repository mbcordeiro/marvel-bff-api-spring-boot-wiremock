package com.matheuscordeiro.bffmarvelapi.service;

import com.matheuscordeiro.bffmarvelapi.client.dto.Character;
import com.matheuscordeiro.bffmarvelapi.client.ports.MarvelClient;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelCharacter;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelComics;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelEvents;
import com.matheuscordeiro.bffmarvelapi.exception.CharactersNotFoundException;
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
    private static final String PORTRAIT_SMALL = "portrait_small";
    private static final String PORTRAIT_MEDIUM = "portrait_medium";
    private static final String PORTRAIT_XLARGE = "portrait_xlarge";
    private static final String PORTRAIT_FANTASTIC = "portrait_fantastic";
    private static final String PORTRAIT_UNCANNY = "portrait_uncanny";
    private static final String PORTRAIT_INCREDIBLE = "portrait_incredible";

    private static final String STANDARD_SMALL = "standard_small";
    private static final String STANDARD_MEDIUM = "standard_medium";
    private static final String STANDARD_LARGE = "standard_large";
    private static final String STANDARD_XLARGE = "standard_xlarge";
    private static final String STANDARD_FANTASTIC = "standard_fantastic";
    private static final String STANDARD_AMAZING = "standard_amazing";

    private static final String LANDSCAPE_SMALL = "landscape_small";
    private static final String LANDSCAPE_MEDIUM = "landscape_medium";
    private static final String LANDSCAPE_LARGE = "landscape_large";
    private static final String LANDSCAPE_XLARGE = "landscape_xlarge";
    private static final String LANDSCAPE_AMAZING = "landscape_amazing";
    private static final String LANDSCAPE_INCREDIBLE = "landscape_incredible";

    private final MarvelClient client;

    private final FileService fileService;

    @Async
    private void getCharacterImage(Character character, String size) {
        try {
            final var response = client.image(new URI(String.format("%s/%s.jpg", character.getThumbnail().getPath(), size)));
            fileService.saveFile(response.getInputStream(),
                    String.format("%s_%s_%s.%s", character.getName().toUpperCase(), character.getId(), size,
                            character.getThumbnail().getExtension()));
        } catch (URISyntaxException | IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public List<MarvelCharacter> findCharacters(String name) {
        List<MarvelCharacter> marvelCharacters = new ArrayList<>();
        final var listCharacters = client.listCharacters(name, null, null, null, null, null,
                null, null, null, null);

        if (!listCharacters.getData().getResults().isEmpty()) {
            listCharacters.getData().getResults().forEach(character -> {
                MarvelCharacter marvelCharacter = new MarvelCharacter();

                marvelCharacter.setDescription(character.getDescription());
                marvelCharacter.setId(character.getId());
                marvelCharacter.setName(character.getName());

                marvelCharacter.setComics(findComicsByCharacter(String.valueOf(character.getId())));
                marvelCharacter.setEvents(findEventsByCharacter(String.valueOf(character.getId())));

                marvelCharacters.add(marvelCharacter);

                log.info("Obtendo imagens...");

                getCharacterImage(character, PORTRAIT_SMALL);
                getCharacterImage(character, PORTRAIT_MEDIUM);
                getCharacterImage(character, PORTRAIT_XLARGE);
                getCharacterImage(character, PORTRAIT_FANTASTIC);
                getCharacterImage(character, PORTRAIT_UNCANNY);
                getCharacterImage(character, PORTRAIT_INCREDIBLE);

                getCharacterImage(character, STANDARD_SMALL);
                getCharacterImage(character, STANDARD_MEDIUM);
                getCharacterImage(character, STANDARD_LARGE);
                getCharacterImage(character, STANDARD_XLARGE);
                getCharacterImage(character, STANDARD_FANTASTIC);
                getCharacterImage(character, STANDARD_AMAZING);

                getCharacterImage(character, LANDSCAPE_SMALL);
                getCharacterImage(character, LANDSCAPE_MEDIUM);
                getCharacterImage(character, LANDSCAPE_LARGE);
                getCharacterImage(character, LANDSCAPE_XLARGE);
                getCharacterImage(character, LANDSCAPE_AMAZING);
                getCharacterImage(character, LANDSCAPE_INCREDIBLE);

                log.info("Saved images !!!");
            });
        } else {
            throw new CharactersNotFoundException("Characters not found. Should be from competitor !!!");
        }

        return marvelCharacters;
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
