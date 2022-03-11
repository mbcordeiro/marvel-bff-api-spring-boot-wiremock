package com.matheuscordeiro.bffmarvelapi.client.feign;

import com.matheuscordeiro.bffmarvelapi.client.dto.ComicDataWrapper;
import com.matheuscordeiro.bffmarvelapi.client.dto.EventDataWrapper;
import com.matheuscordeiro.bffmarvelapi.client.dto.InlineResponse200;
import com.matheuscordeiro.bffmarvelapi.configuration.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.net.URI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(contextId = "MarvelApiClient", name = "${marvelPublicAPIV1.name:marvelPublicAPIV1}", url = "${marvelPublicAPIV1.url:http://gateway.marvel.com/v1/public}", configuration = ClientConfiguration.class)
@Api(value = "Default")
@Validated
public interface MarvelApi {
    @GetMapping
    ResponseEntity<Resource> image(URI baseUri);

    @ApiOperation(value = "List characters", nickname = "listCharacters", notes = "Fetches lists of characters.", response = InlineResponse200.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = InlineResponse200.class) })
    @GetMapping(value = "/characters", produces = "*/*")
    ResponseEntity<InlineResponse200> listCharacters(
            @RequestParam String ts,
            @RequestParam String apikey,
            @RequestParam String hash,
            @ApiParam(value = "Return only characters matching the specified full character name (e.g. Spider-Man).") @Valid @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "Return characters with names that begin with the specified string (e.g. Sp).") @Valid @RequestParam(value = "nameStartsWith", required = false) String nameStartsWith,
            @ApiParam(value = "Return only characters which have been modified since the specified date.") @Valid @RequestParam(value = "modifiedSince", required = false) String modifiedSince,
            @ApiParam(value = "Return only characters which appear in the specified comics (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "comics", required = false) BigDecimal comics,
            @ApiParam(value = "Return only characters which appear the specified series (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "series", required = false) BigDecimal series,
            @ApiParam(value = "Return only characters which appear in the specified events (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "events", required = false) BigDecimal events,
            @ApiParam(value = "Return only characters which appear the specified stories (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "stories", required = false) BigDecimal stories,
            @ApiParam(value = "Order the result set by a field or fields. Add a \"-\" to the value sort in descending order. Multiple values are given priority in the order in which they are passed.") @Valid @RequestParam(value = "orderBy", required = false) String orderBy,
            @ApiParam(value = "Limit the result set to the specified number of resources.") @Valid @RequestParam(value = "limit", required = false) BigDecimal limit,
            @ApiParam(value = "Skip the specified number of resources in the result set.") @Valid @RequestParam(value = "offset", required = false) BigDecimal offset);


    @ApiOperation(value = "Character comics", nickname = "characterComics", notes = "Fetches lists of comics filtered by a character id.", response = ComicDataWrapper.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ComicDataWrapper.class) })
    @GetMapping(value = "/characters/{characterId}/comics", produces = "*/*")
    ResponseEntity<ComicDataWrapper> characterComics(
            @RequestParam String ts,
            @RequestParam String apikey,
            @RequestParam String hash,
            @ApiParam(value = "The character id.", required = true) @PathVariable("characterId") String characterId,
            @ApiParam(value = "Filter by the issue format (e.g. comic, digital comic, hardcover).") @Valid @RequestParam(value = "format", required = false) String format,
            @ApiParam(value = "Filter by the issue format type (comic or collection).") @Valid @RequestParam(value = "formatType", required = false) String formatType,
            @ApiParam(value = "Exclude variant comics from the result set.") @Valid @RequestParam(value = "noVariants", required = false) Boolean noVariants,
            @ApiParam(value = "Return comics within a predefined date range.") @Valid @RequestParam(value = "dateDescriptor", required = false) String dateDescriptor,
            @ApiParam(value = "Return comics within a predefined date range.  Dates must be specified as date1,date2 (e.g. 2013-01-01,2013-01-02).  Dates are preferably formatted as YYYY-MM-DD but may be sent as any common date format.") @Valid @RequestParam(value = "dateRange", required = false) BigDecimal dateRange,
            @ApiParam(value = "Filter by diamond code.") @Valid @RequestParam(value = "diamondCode", required = false) String diamondCode,
            @ApiParam(value = "Filter by digital comic id.") @Valid @RequestParam(value = "digitalId", required = false) BigDecimal digitalId,
            @ApiParam(value = "Filter by UPC.") @Valid @RequestParam(value = "upc", required = false) String upc,
            @ApiParam(value = "Filter by ISBN.") @Valid @RequestParam(value = "isbn", required = false) String isbn,
            @ApiParam(value = "Filter by EAN.") @Valid @RequestParam(value = "ean", required = false) String ean,
            @ApiParam(value = "Filter by ISSN.") @Valid @RequestParam(value = "issn", required = false) String issn,
            @ApiParam(value = "Include only results which are available digitally.") @Valid @RequestParam(value = "hasDigitalIssue", required = false) Boolean hasDigitalIssue,
            @ApiParam(value = "Return only comics which have been modified since the specified date.") @Valid @RequestParam(value = "modifiedSince", required = false) String modifiedSince,
            @ApiParam(value = "Return only comics which feature work by the specified creators (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "creators", required = false) BigDecimal creators,
            @ApiParam(value = "Return only comics which are part of the specified series (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "series", required = false) BigDecimal series,
            @ApiParam(value = "Return only comics which take place in the specified events (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "events", required = false) BigDecimal events,
            @ApiParam(value = "Return only comics which contain the specified stories (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "stories", required = false) BigDecimal stories,
            @ApiParam(value = "Return only comics in which the specified characters appear together (for example in which BOTH Spider-Man and Wolverine appear).") @Valid @RequestParam(value = "sharedAppearances", required = false) BigDecimal sharedAppearances,
            @ApiParam(value = "Return only comics in which the specified creators worked together (for example in which BOTH Stan Lee and Jack Kirby did work).") @Valid @RequestParam(value = "collaborators", required = false) BigDecimal collaborators,
            @ApiParam(value = "Order the result set by a field or fields. Add a \"-\" to the value sort in descending order. Multiple values are given priority in the order in which they are passed.") @Valid @RequestParam(value = "orderBy", required = false) String orderBy,
            @ApiParam(value = "Limit the result set to the specified number of resources.") @Valid @RequestParam(value = "limit", required = false) BigDecimal limit,
            @ApiParam(value = "Skip the specified number of resources in the result set.") @Valid @RequestParam(value = "offset", required = false) BigDecimal offset);

    @ApiOperation(value = "Character events", nickname = "characterEvents", notes = "Fetches lists of events filtered by a character id.", response = EventDataWrapper.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = EventDataWrapper.class) })
    @GetMapping(value = "/characters/{characterId}/events", produces = "*/*")
    ResponseEntity<EventDataWrapper> characterEvents(
            @RequestParam String ts,
            @RequestParam String apikey,
            @RequestParam String hash,
            @ApiParam(value = "The character ID.", required = true) @PathVariable("characterId") String characterId,
            @ApiParam(value = "Filter the event list by name.") @Valid @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "Return events with names that begin with the specified string (e.g. Sp).") @Valid @RequestParam(value = "nameStartsWith", required = false) String nameStartsWith,
            @ApiParam(value = "Return only events which have been modified since the specified date.") @Valid @RequestParam(value = "modifiedSince", required = false) String modifiedSince,
            @ApiParam(value = "Return only events which feature work by the specified creators (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "creators", required = false) BigDecimal creators,
            @ApiParam(value = "Return only events which are part of the specified series (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "series", required = false) BigDecimal series,
            @ApiParam(value = "Return only events which take place in the specified comics (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "comics", required = false) BigDecimal comics,
            @ApiParam(value = "Return only events which contain the specified stories (accepts a comma-separated list of ids).") @Valid @RequestParam(value = "stories", required = false) BigDecimal stories,
            @ApiParam(value = "Order the result set by a field or fields. Add a \"-\" to the value sort in descending order. Multiple values are given priority in the order in which they are passed.") @Valid @RequestParam(value = "orderBy", required = false) String orderBy,
            @ApiParam(value = "Limit the result set to the specified number of resources.") @Valid @RequestParam(value = "limit", required = false) BigDecimal limit,
            @ApiParam(value = "Skip the specified number of resources in the result set.") @Valid @RequestParam(value = "offset", required = false) BigDecimal offset);
}
