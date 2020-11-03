package com.example.pokeIndex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonAbilityDto {
    @JsonProperty("name")
    String name;
    @JsonProperty("effect_entries")
    List<String> effectDescriptions;
    @JsonProperty("pokemon")
    List<String> pokemons;

    public PokemonAbilityDto() {
    }

    public PokemonAbilityDto(String name, List<String> effectDescriptions, List<String> pokemons) {
        this.name = name;
        this.effectDescriptions = effectDescriptions;
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEffectDescriptions() {
        return effectDescriptions;
    }

    public void setEffectDescriptions(List<String> effectDescriptions) {
        this.effectDescriptions = effectDescriptions;
    }

    public List<String> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<String> pokemons) {
        this.pokemons = pokemons;
    }
}

