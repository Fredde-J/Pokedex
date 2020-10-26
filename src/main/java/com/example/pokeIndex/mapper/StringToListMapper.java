package com.example.pokeIndex.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToListMapper {
    List<String> asList(String str) {
        return List.of(str.split(", "));
    }

    String asString(List<String> list) {
        return String.join(", ", list);
    }
}
