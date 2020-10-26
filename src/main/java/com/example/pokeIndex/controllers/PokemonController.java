package com.example.pokeIndex.controllers;

import com.example.pokeIndex.dto.PokemonDto;
import com.example.pokeIndex.entities.Pokemon;
import com.example.pokeIndex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> findPokemonByName(@RequestParam String name) {
        if (name == null) {
            System.out.println("name is null");
        }
        System.out.println(name);
        var pokemons = pokemonService.findAll(name);

        return ResponseEntity.ok(pokemons);

    }
}
