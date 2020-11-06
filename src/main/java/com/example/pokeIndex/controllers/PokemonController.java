package com.example.pokeIndex.controllers;

import com.example.pokeIndex.entities.Pokemon;
import com.example.pokeIndex.services.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Find a pokemons with a name,ability,type or weight or search with all four attributes, if all is empty all pokemons will show up.  No authentication needed")
    @GetMapping
    public ResponseEntity<List<Pokemon>> findPokemons(@Parameter(description = "name to be searched") @RequestParam(required = false) String name,
                                                      @RequestParam(required = false)String ability,@RequestParam(required = false)String type,
                                                      @RequestParam(required = false) Integer weight ) {
        var pokemons = pokemonService.findAll(name,ability,type,weight);
        return ResponseEntity.ok(pokemons);
    }

    @Operation(summary = "Find a pokemon with a id, no authentication needed")
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findByPokemonById(@PathVariable String id) {
        return ResponseEntity.ok(pokemonService.findById(id));
    }

    @Operation(summary = "Add a new pokemon to the database, need role Admin to access")
    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(pokemonService.save(pokemon));
    }

    @Operation(summary = "Update a pokemon by id, need role Admin to access")
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @Operation(summary = "Delete a pokemon by id, need role Admin to access")
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable String id) {
        pokemonService.delete(id);
    }
}
