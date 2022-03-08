package com.matheuscordeiro.bffmarvelapi.client.ports;

import java.math.BigDecimal;
import java.net.URI;

import com.matheuscordeiro.bffmarvelapi.client.dto.InlineResponse200;
import org.springframework.core.io.Resource;


public interface MarvelClient {
    Resource image(URI baseUri);

    InlineResponse200 listCharacters(String name, String nameStartsWith, String modifiedSince, BigDecimal comics,
                                     BigDecimal series, BigDecimal events, BigDecimal stories, String orderBy, BigDecimal limit,
                                     BigDecimal offset);
}