package com.example.pokeIndex.entities;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Pokemon implements Serializable {
    private static final long serialVersionUID = 669636611022282531L;
    @Id
    private String id;
    private String name;
    private List<Object> abilities;
    private List<Object> stats;
    private List<Object> types;

    public Pokemon() {

    }

    public Pokemon(String name, List<Object> abilities, List<Object> stats, String species,List<Object> types) {
        this.name = name;
        this.abilities = abilities;
        this.stats = stats;
        this.types = types;
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
