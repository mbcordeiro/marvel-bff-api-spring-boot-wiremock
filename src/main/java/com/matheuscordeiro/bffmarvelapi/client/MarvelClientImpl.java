package com.matheuscordeiro.bffmarvelapi.client;

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
    public InlineResponse200 listCharacters(@Valid String name, @Valid String nameStartsWith,
                                            @Valid String modifiedSince, @Valid BigDecimal comics, @Valid BigDecimal series, @Valid BigDecimal events,
                                            @Valid BigDecimal stories, @Valid String orderBy, @Valid BigDecimal limit, @Valid BigDecimal offset) {
        return marvelApi.listCharacters(ts, apiKey, hash, name, nameStartsWith, modifiedSince, comics, series, events,
                stories, orderBy, limit, offset).getBody();
    }
}
