package com.matheuscordeiro.bffmarvelapi.controller;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.matheuscordeiro.bffmarvelapi.BffMarvelApiApplication;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WireMockTest(httpPort = 8085)
@SpringBootTest(classes = BffMarvelApiApplication.class)
public class CharacterControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
}
