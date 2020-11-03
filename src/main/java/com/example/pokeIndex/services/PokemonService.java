package com.example.pokeIndex.services;

import com.example.pokeIndex.dto.PokemonDto;
import com.example.pokeIndex.entities.Pokemon;
import com.example.pokeIndex.entities.PokemonName;
import com.example.pokeIndex.mapper.PokemonMapper;
import com.example.pokeIndex.repositories.PokemonNameRepo;
import com.example.pokeIndex.repositories.PokemonsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PokemonService {
    @Autowired
    private PokemonMapper pokemonMapper;
    @Autowired
    private PokemonsRepo pokemonsRepo;
    @Autowired
    private PokemonConsumerService pokemonConsumerService;
    @Autowired
    private PokemonNameRepo pokemonsNameRepo;


    public List<Pokemon> findAll(String name) {
        if (name == null) {
            return pokemonsRepo.findAll();
        }
        if (name.length() < 3) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("You need at least 3 letters to search pokemons by name"));
        }

        List<Pokemon> pokemons = new ArrayList<>();
        List<PokemonName> pokemonNames = new ArrayList<>();
        try {
            pokemons = pokemonsRepo.findByNameRegexQuery(name);
            pokemonNames = pokemonsNameRepo.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Object> pokemonNamesThatEqualsName = pokemonNames.get(0).getNames().stream().filter(pokemonName -> pokemonName.toString().contains(name.toLowerCase())).collect(Collectors.toList());

        if (pokemons.size() < pokemonNamesThatEqualsName.size()) {
            var pokemonsDtos = pokemonConsumerService.searchPokemons(pokemonNamesThatEqualsName);
            if (pokemonsDtos != null) {
                for (PokemonDto pokemonDto : pokemonsDtos) {
                    var pokemon = pokemonMapper.pokemonDtoToPokemon(pokemonDto);
                    pokemonsRepo.save(pokemon);
                    pokemons.add(pokemon);
                }
            }
        }
        if (pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find pokemons with name: " + name));
        }
        return pokemons;
    }



    public List<Pokemon> findByAbilityAndType(String ability, String type) {
        var pokemons = pokemonsRepo.findByAbilityType(ability, type);
        if (pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("no pokemons found with those requirements"));
        }
        return pokemons;
    }

    public List<Pokemon> findByAbilityTypeHeightWeight(String ability, String type, int height, int weight) {
        var pokemons = pokemonsRepo.findByAbilityTypeHeightWeight(ability, type, height, weight);
        if (pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("no pokemons found with those requirements"));
        }
        return pokemons;
    }

    public Pokemon findById(String id) {
        return pokemonsRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("no pokemon found with id: " + id)));
    }

    public Pokemon save(Pokemon pokemon) {
        return pokemonsRepo.save(pokemon);
    }

    public void update(String id, Pokemon pokemon) {
        if (!pokemonsRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("could not find pokemon by id:" + id));
        }
        pokemon.setId(id);
        pokemonsRepo.save(pokemon);
    }

    public void delete(String id) {
        if (!pokemonsRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, String.format("could not fin user by id" + id));
        }
        pokemonsRepo.deleteById(id);
    }


}
