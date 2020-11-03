package com.example.pokeIndex.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

public class PokemonAbility implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Indexed(unique=true)
    String name;
    List<String> effectDescriptions;
    List<String> pokemons;

    public PokemonAbility() {
    }

    public PokemonAbility(String name, List<String> effect, List<String> pokemons) {
        this.name = name;
        this.effectDescriptions = effect;
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
