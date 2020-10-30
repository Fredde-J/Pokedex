package com.example.pokeIndex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PokemonDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("abilities")
    private List<Object> abilities = new ArrayList<>();
    @JsonProperty("stats")
    private List<Object> stats;
    @JsonProperty("types")
    private List<Object> types;


    public PokemonDto() {

    }

    public PokemonDto(String name, List<Object> abilities, List<Object> stats , List<Object> types ) {
        this.name = name;
        this.abilities = abilities;
        this.stats = stats;
        this.types = types;

       // Pokemon pokemon = new Pokemon(name,abilities,stats,species);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Object> abilities) {
        this.abilities = abilities;
    }

    public List<Object> getStats() {
        return stats;
    }

    public void setStats(List<Object> stats) {
        this.stats = stats;
    }

    public List<Object> getTypes() {
        return types;
    }

    public void setTypes(List<Object> types) {
        this.types = types;
    }
}
