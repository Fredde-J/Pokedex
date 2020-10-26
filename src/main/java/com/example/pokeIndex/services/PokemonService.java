package com.example.pokeIndex.services;
import com.example.pokeIndex.entities.Pokemon;
import com.example.pokeIndex.mapper.PokemonMapper;
import com.example.pokeIndex.repositories.PokemonsNameRepo;
import com.example.pokeIndex.repositories.PokemonsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class PokemonService {
    @Autowired
    private PokemonMapper pokemonMapper;
    @Autowired
    private PokemonsRepo pokemonsRepo;
    @Autowired
    private PokemonConsumerService pokemonConsumerService;
    @Autowired
    private PokemonsNameRepo pokemonsNameRepo;


    public List<Pokemon> findAll(String name){
        name = name.toLowerCase();
        pokemonsNameRepo.GetAllNames();
        var pokemons = pokemonsRepo.findAll();


        if(pokemons.isEmpty()){
            var pokemonsDto = pokemonConsumerService.searchPokemon(name);
            if(pokemonsDto!=null){
                System.out.println("pokemon: "+ pokemonsDto.getName());
                var pokemon = pokemonMapper.pokemonDtoToPokemon(pokemonsDto);
                pokemonsRepo.save(pokemon);
                pokemons.add(pokemon);
            }
        }
        return pokemons;

    };
}
