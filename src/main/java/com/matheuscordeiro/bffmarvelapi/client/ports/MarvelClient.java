package com.matheuscordeiro.bffmarvelapi.client.ports;

import java.math.BigDecimal;
import java.net.URI;

import com.matheuscordeiro.bffmarvelapi.client.dto.ComicDataWrapper;
import com.matheuscordeiro.bffmarvelapi.client.dto.EventDataWrapper;
import com.matheuscordeiro.bffmarvelapi.client.dto.InlineResponse200;
import org.springframework.core.io.Resource;


public interface MarvelClient {
    Resource image(URI baseUri);

    ComicDataWrapper characterComics(String characterId, String format, String formatType,
                                     Boolean noVariants, String dateDescriptor, BigDecimal dateRange, String diamondCode, BigDecimal digitalId,
                                     String upc, String isbn, String ean, String issn, Boolean hasDigitalIssue, String modifiedSince,
                                     BigDecimal creators, BigDecimal series, BigDecimal events, BigDecimal stories, BigDecimal sharedAppearances,
                                     BigDecimal collaborators, String orderBy, BigDecimal limit, BigDecimal offset);

    EventDataWrapper characterEvents(String characterId, String name, String nameStartsWith,
                                     String modifiedSince, BigDecimal creators, BigDecimal series, BigDecimal comics, BigDecimal stories,
                                     String orderBy, BigDecimal limit, BigDecimal offset);

    InlineResponse200 listCharacters(String name, String nameStartsWith, String modifiedSince, BigDecimal comics,
                                     BigDecimal series, BigDecimal events, BigDecimal stories, String orderBy, BigDecimal limit,
                                     BigDecimal offset);
}