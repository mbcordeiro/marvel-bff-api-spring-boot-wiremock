package com.matheuscordeiro.bffmarvelapi.client;

import com.matheuscordeiro.bffmarvelapi.client.dto.InlineResponse200;
import com.matheuscordeiro.bffmarvelapi.client.ports.MarvelClient;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;

@Component
public class MarvelClientImpl implements MarvelClient {
    @Override
    public Resource image(URI baseUri) {
        return null;
    }

    @Override
    public InlineResponse200 listCharacters(String name, String nameStartsWith, String modifiedSince, BigDecimal comics, BigDecimal series, BigDecimal events, BigDecimal stories, String orderBy, BigDecimal limit, BigDecimal offset) {
        return null;
    }
}
