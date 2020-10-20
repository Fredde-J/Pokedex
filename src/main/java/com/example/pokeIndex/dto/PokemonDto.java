package com.example.pokeIndex.dto;

import com.example.pokeIndex.entities.Pokemon;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PokemonDto {
    @Autowired
    private Pokemon pokemon = new Pokemon();

    @JsonProperty("name")
    private String name;
    @JsonProperty("abilities")
    private List<String> abilities;
    @JsonProperty("stats")
    private List<String> stats;
    @JsonProperty("species")
    private String species;

    public PokemonDto(String name, List<String> abilities, List<String> stats, String species) {
        this.name = name;
        this.abilities = abilities;
        this.stats = stats;
        this.species = species;

        Pokemon pokemon = new Pokemon(name,abilities,stats,species);
    }
}
