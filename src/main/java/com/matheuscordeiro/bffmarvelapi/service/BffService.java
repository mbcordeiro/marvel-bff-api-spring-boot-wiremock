package com.matheuscordeiro.bffmarvelapi.service;

import java.util.List;

public interface BffService {
    List<Object> findCharacters(String name);
}
