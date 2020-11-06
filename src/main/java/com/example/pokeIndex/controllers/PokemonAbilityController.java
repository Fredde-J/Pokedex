package com.example.pokeIndex.controllers;

import com.example.pokeIndex.entities.PokemonAbility;
import com.example.pokeIndex.services.PokemonAbilityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/pokemonsAbilities")
public class PokemonAbilityController {

    @Autowired
    PokemonAbilityService pokemonAbilityService;

    @Operation(summary = "Find a ability with a name. if name is empty all abilities from db will show up, need role user or admin to access")
    @GetMapping
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<List<PokemonAbility>> findAllAbilitiesByName(@RequestParam(required = false) String name) {
        var pokemonAbilities = pokemonAbilityService.findAllByName(name);
        return ResponseEntity.ok(pokemonAbilities);
    }

    @Operation(summary = "Find a ability with a id, need role user or admin to access")
    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<PokemonAbility> findAbilityById(@PathVariable String id) {
        return ResponseEntity.ok(pokemonAbilityService.findById(id));
    }

    @Operation(summary = "Add a new ability, need role admin to access ")
    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<PokemonAbility> saveAbility(@Validated @RequestBody PokemonAbility pokemonAbility) {
        return ResponseEntity.ok(pokemonAbilityService.save(pokemonAbility));
    }

    @Operation(summary = "Update a ability by id, need role admin to access")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void updateAbility(@PathVariable String id, @Validated @RequestBody PokemonAbility pokemonAbility)
    {
        pokemonAbilityService.update(id, pokemonAbility);
    }

    @Operation(summary = "Delete a ability by id, need role admin to access")
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAbility(@PathVariable String id) {
        pokemonAbilityService.delete(id);
    }


}
