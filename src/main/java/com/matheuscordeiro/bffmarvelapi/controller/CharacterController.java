package com.matheuscordeiro.bffmarvelapi.controller;

import com.matheuscordeiro.bffmarvelapi.dto.MarvelCharacter;
import com.matheuscordeiro.bffmarvelapi.service.ports.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService bffService;

    @GetMapping(value = "/marvel/heros/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MarvelCharacter>> findCharacters(@PathVariable("name") String name) {
        return ResponseEntity.ok(bffService.findCharacters(name));
    }
}
