package com.example.pokeIndex.entities;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

public class PokemonName  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private List<Object>  names;

    public PokemonName() {
    }

    public PokemonName( List<Object> names) {
        this.names = names;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Object> getNames() {
        return names;
    }

    public void setNames(List<Object> names) {
        this.names = names;
    }
}
