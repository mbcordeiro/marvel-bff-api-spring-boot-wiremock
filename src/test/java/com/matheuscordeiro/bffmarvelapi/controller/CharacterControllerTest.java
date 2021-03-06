package com.matheuscordeiro.bffmarvelapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.matheuscordeiro.bffmarvelapi.BffMarvelApiApplication;
import com.matheuscordeiro.bffmarvelapi.client.utils.ResourceUtils;
import com.matheuscordeiro.bffmarvelapi.configuration.ClientConfiguration;
import com.matheuscordeiro.bffmarvelapi.dto.MarvelCharacter;
import com.matheuscordeiro.bffmarvelapi.utils.Constants;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

@WireMockTest(httpPort = 8085)
@SpringBootTest(classes = BffMarvelApiApplication.class)
@TestPropertySource(locations = "classpath:application-bffControllerTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CharacterControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ClientConfiguration configuration;

    @Value("classpath:json/listCharacters_OK.json")
    private Resource listCharactersOK;

    @Value("classpath:json/characterComics_OK.json")
    private Resource characterComicsOK;

    @Value("classpath:json/characterEvents_OK.json")
    private Resource characterEventsOK;

    @Value("classpath:json/listCharacters_NotFound.json")
    private Resource listCharactersNotFound;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    @Order(1)
    @DisplayName("1 - Efetuando a busca de um personagem de acordo com o crit??rio de pesquisa")
    public void testFindCharacters() throws Exception {
        WireMock.stubFor(WireMock
                .get(String.format("/v1/public/characters?ts=%s&apikey=%s&hash=%s&name=%s", configuration.getTs(),
                        configuration.getApiKey(), configuration.getHash(), Constants.CHARACTERS_NAME))
                .willReturn(WireMock.aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(listCharactersOK))));

        WireMock.stubFor(WireMock
                .get(String.format("/v1/public/characters/%s/comics?ts=%s&apikey=%s&hash=%s&orderBy=-focDate",
                        Constants.CHARACTERS_ID, configuration.getTs(), configuration.getApiKey(),
                        configuration.getHash()))
                .willReturn(WireMock.aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(characterComicsOK))));

        WireMock.stubFor(WireMock
                .get(String.format("/v1/public/characters/%s/events?ts=%s&apikey=%s&hash=%s", Constants.CHARACTERS_ID,
                        configuration.getTs(), configuration.getApiKey(), configuration.getHash()))
                .willReturn(WireMock.aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(characterEventsOK))));

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(String.format("/marvel/heros/%s", Constants.CHARACTERS_NAME)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<MarvelCharacter> result = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<MarvelCharacter>>() {
                });

        assertFalse(result.isEmpty());
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getId(), equalTo(BigDecimal.valueOf(Long.parseLong(Constants.CHARACTERS_ID))));
        assertThat(result.get(0).getName().toLowerCase(), equalTo(Constants.CHARACTERS_NAME.toLowerCase()));

        assertFalse(result.get(0).getComics().isEmpty());
        assertThat(result.get(0).getComics().size(), equalTo(20));

        assertFalse(result.get(0).getEvents().isEmpty());
        assertThat(result.get(0).getEvents().size(), equalTo(20));
    }

    @Test
    @Order(2)
    @DisplayName("2 - Personagem n??o encontrado")
    public void testNotFoundCharacter() throws Exception {
        WireMock.stubFor(WireMock
                .get(String.format("/v1/public/characters?ts=%s&apikey=%s&hash=%s&name=%s", configuration.getTs(),
                        configuration.getApiKey(), configuration.getHash(), Constants.CHARACTERS_NAME_NOT_FOUND))
                .willReturn(WireMock.aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(listCharactersNotFound))));

        mockMvc.perform(
                        MockMvcRequestBuilders.get(String.format("/marvel/heros/%s", Constants.CHARACTERS_NAME_NOT_FOUND)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
