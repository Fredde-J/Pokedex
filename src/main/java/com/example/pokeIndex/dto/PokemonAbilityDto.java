package com.example.pokeIndex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonAbilityDto {
    @JsonProperty("name")
    String name;
    @JsonProperty("effect_entries")
    List<Object> effectDescriptions;
    @JsonProperty("pokemon")
    List<Object> pokemons;

    public PokemonAbilityDto() {
    }

    public PokemonAbilityDto(String name, List<Object> effectDescriptions, List<Object> pokemons) {
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

    public List<Object> getEffectDescriptions() {
        return effectDescriptions;
    }

    public void setEffectDescriptions(List<Object> effectDescriptions) {
        this.effectDescriptions = effectDescriptions;
    }

    public List<Object> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Object> pokemons) {
        this.pokemons = pokemons;
    }
}

