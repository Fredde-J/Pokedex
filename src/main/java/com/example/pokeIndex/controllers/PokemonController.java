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

    @Operation(summary = "find a pokemon with a name with at least 3 letters. if name is empty all pokemons from db will show up")
    @GetMapping
    public ResponseEntity<List<Pokemon>> findPokemons(@Parameter(description = "name to be searched") @RequestParam(required = false) String name,
                                                      @RequestParam(required = false)String ability,@RequestParam(required = false)String type,
                                                      @RequestParam(required = false) Integer weight ) {
        var pokemons = pokemonService.findAll(name,ability,type,weight);
        return ResponseEntity.ok(pokemons);
    }

    @Operation(summary = "Find a pokemon with a id")
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findByPokemonById(@PathVariable String id) {
        return ResponseEntity.ok(pokemonService.findById(id));
    }
/*
    @Operation(summary = "Find all pokemons that have the a certain ability and type")
    @GetMapping("/{ability}/{type}")
    public ResponseEntity<List<Pokemon>> findPokemonByAbilityAndType(@PathVariable String ability, @PathVariable String type) {
        var pokemons = pokemonService.findByAbilityAndType(ability, type);
        return ResponseEntity.ok(pokemons);
    }



    @Operation(summary = "Find all pokemons that have the a certain ability,type,height and weight")
    @GetMapping("/{ability}/{type}/{height}/{weight}")
    public ResponseEntity<List<Pokemon>> findPokemonByAbilityTypeHeightWeight(@PathVariable String ability, @PathVariable String type, @PathVariable int height, @PathVariable int weight) {
        System.out.println("ok");
        var pokemons = pokemonService.findByAbilityTypeHeightWeight(ability, type, height, weight);
        return ResponseEntity.ok(pokemons);
    }

 */

    @Operation(summary = "Add a new pokemon to the database")
    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(pokemonService.save(pokemon));
    }

    @Operation(summary = "Update a pokemon by id")
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @Operation(summary = "Delete a pokemon by id")
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable String id) {
        pokemonService.delete(id);
    }
}
