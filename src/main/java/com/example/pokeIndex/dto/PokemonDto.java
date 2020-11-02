package com.example.pokeIndex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PokemonDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("abilities")
    private List<Object> abilities = new ArrayList<>();
    @JsonProperty("types")
    private List<Object> types;
    @JsonProperty("height")
    private int height;
    @JsonProperty("weight")
    private int weight;



    public PokemonDto() {

    }

    public PokemonDto(String name, List<Object> abilities, List<Object> types, int height, int weight) {
        this.name = name;
        this.abilities = abilities;
        this.types = types;
        this.height = height;
        this.weight = weight;
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

    public List<Object> getTypes() {
        return types;
    }

    public void setTypes(List<Object> types) {
        this.types = types;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
