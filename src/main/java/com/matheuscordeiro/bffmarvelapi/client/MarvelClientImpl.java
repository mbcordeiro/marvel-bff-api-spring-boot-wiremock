package com.matheuscordeiro.bffmarvelapi.client;

import com.matheuscordeiro.bffmarvelapi.client.dto.ComicDataWrapper;
import com.matheuscordeiro.bffmarvelapi.client.dto.EventDataWrapper;
import com.matheuscordeiro.bffmarvelapi.client.dto.InlineResponse200;
import com.matheuscordeiro.bffmarvelapi.client.feign.MarvelApi;
import com.matheuscordeiro.bffmarvelapi.client.ports.MarvelClient;
import com.matheuscordeiro.bffmarvelapi.configuration.ClientConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class MarvelClientImpl implements MarvelClient {
    private final MarvelApi marvelApi;

    private final ClientConfiguration configuration;

    private String ts = null;
    private String apiKey = null;
    private String hash = null;

    @PostConstruct
    public void postConstruct() {
        ts = configuration.getTs();
        apiKey = configuration.getApiKey();
        hash = configuration.getHash();
    }

    @Override
    public Resource image(URI baseUri) {
        return marvelApi.image(baseUri).getBody();
    }

    @Override
    public ComicDataWrapper characterComics(String characterId, @Valid String format, @Valid String formatType,
                                            @Valid Boolean noVariants, @Valid String dateDescriptor, @Valid BigDecimal dateRange,
                                            @Valid String diamondCode, @Valid BigDecimal digitalId, @Valid String upc, @Valid String isbn,
                                            @Valid String ean, @Valid String issn, @Valid Boolean hasDigitalIssue, @Valid String modifiedSince,
                                            @Valid BigDecimal creators, @Valid BigDecimal series, @Valid BigDecimal events, @Valid BigDecimal stories,
                                            @Valid BigDecimal sharedAppearances, @Valid BigDecimal collaborators, @Valid String orderBy,
                                            @Valid BigDecimal limit, @Valid BigDecimal offset) {
        return marvelApi
                .characterComics(ts, apiKey, hash, characterId, format, formatType, noVariants, dateDescriptor,
                        dateRange, diamondCode, digitalId, upc, isbn, ean, issn, hasDigitalIssue, modifiedSince,
                        creators, series, events, stories, sharedAppearances, collaborators, orderBy, limit, offset)
                .getBody();
    }

    @Override
    public EventDataWrapper characterEvents(String characterId, @Valid String name, @Valid String nameStartsWith,
                                            @Valid String modifiedSince, @Valid BigDecimal creators, @Valid BigDecimal series, @Valid BigDecimal comics,
                                            @Valid BigDecimal stories, @Valid String orderBy, @Valid BigDecimal limit, @Valid BigDecimal offset) {
        return marvelApi.characterEvents(ts, apiKey, hash, characterId, name, nameStartsWith, modifiedSince, creators,
                series, comics, stories, orderBy, limit, offset).getBody();
    }

    @Override
    public InlineResponse200 listCharacters(@Valid String name, @Valid String nameStartsWith,
                                            @Valid String modifiedSince, @Valid BigDecimal comics, @Valid BigDecimal series, @Valid BigDecimal events,
                                            @Valid BigDecimal stories, @Valid String orderBy, @Valid BigDecimal limit, @Valid BigDecimal offset) {
        return marvelApi.listCharacters(ts, apiKey, hash, name, nameStartsWith, modifiedSince, comics, series, events,
                stories, orderBy, limit, offset).getBody();
    }
}
