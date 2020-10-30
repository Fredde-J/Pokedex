package com.example.pokeIndex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class PokemonNameDto {
    @JsonProperty("results")
    private List<Object> names;

    public PokemonNameDto() {
    }

    public PokemonNameDto(List<Object> names) {
        this.names = names;
    }

    public List<Object> getNames() {
        return names;
    }

    public void setNames(List<Object> names) {
        this.names = names;
    }
}
