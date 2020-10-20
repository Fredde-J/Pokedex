package com.example.pokeIndex.entities;
import org.springframework.data.annotation.Id;
import java.util.List;

public class Pokemon {
    @Id
    private String id;
    private String name;
    private List<String> abilities;
    private List<String> stats;
    private String species;

    public Pokemon() {

    }

    public Pokemon(String name, List<String> ablilities, List<String> stats, String species) {
        this.name = name;
        this.abilities = ablilities;
        this.stats = stats;
        this.species = species;
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

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public List<String> getStats() {
        return stats;
    }

    public void setStats(List<String> stats) {
        this.stats = stats;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
