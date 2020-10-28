package com.example.pokeIndex.controllers;

import com.example.pokeIndex.entities.Pokemon;
import com.example.pokeIndex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findByUserById(@PathVariable String id){
        return ResponseEntity.ok(pokemonService.findById(id));
    }
    @GetMapping("/{ability}/{type}")
    public ResponseEntity<List<Pokemon>> findPokemonByAbilityAndType(@PathVariable String ability,@PathVariable String type) {
         var pokemons = pokemonService.findByAbilityAndType(ability, type);
         return ResponseEntity.ok(pokemons);

    }

    @PostMapping
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon){
        return ResponseEntity.ok(pokemonService.save(pokemon));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id,@RequestBody Pokemon pokemon){
        pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id){
        pokemonService.delete(id);
    }
}
