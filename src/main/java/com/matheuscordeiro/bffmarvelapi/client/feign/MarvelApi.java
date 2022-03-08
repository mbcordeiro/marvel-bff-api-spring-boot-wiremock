package com.matheuscordeiro.bffmarvelapi.client.feign;

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
}
