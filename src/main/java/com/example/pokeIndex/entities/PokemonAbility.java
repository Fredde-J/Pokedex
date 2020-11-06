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
    List<Object> effectDescriptions;
    List<Object> pokemons;

    public PokemonAbility() {
    }

    public PokemonAbility(String name, List<Object> effect, List<Object> pokemons) {
        this.name = name;
        this.effectDescriptions = effect;
        this.pokemons = pokemons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
