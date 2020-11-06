package com.example.pokeIndex.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private static final long serialVersionUID = 669636611022282531L;
    @Id
    private String id;
    @Indexed(unique=true)
    private String name;
    private List<Object> abilities;
    private List<Object> types;
    private int height;
    private int weight;

    public Pokemon() {

    }

    public Pokemon(String name, List<Object> abilities, List<Object> types,int height, int weight) {
        this.name = name;
        this.abilities = abilities;
        this.types = types;
        this.height= height;
        this.weight =  weight;
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
