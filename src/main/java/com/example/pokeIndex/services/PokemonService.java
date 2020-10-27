package com.example.pokeIndex.services;

import com.example.pokeIndex.entities.Pokemon;
import com.example.pokeIndex.mapper.PokemonMapper;
import com.example.pokeIndex.repositories.PokemonsNameRepo;
import com.example.pokeIndex.repositories.PokemonsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private PokemonsNameRepo pokemonsNameRepo;


    public List<Pokemon> findAll(String name){
        pokemonsNameRepo.getAllNames();
        var pokemons = pokemonsRepo.findAll();
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
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

    public List<Pokemon> findByAbilityAndType(String ability, String type) {

        System.out.println(ability);
        System.out.println(type);
        var pokemons = pokemonsRepo.findAll();
        System.out.println(pokemons.get(0).getAbilities());
        pokemons  = pokemons.stream()
                .filter(pokemon -> pokemon.getAbilities().toString().contains(ability.toLowerCase()))
                .filter(pokemon -> pokemon.getTypes().toString().contains(type.toLowerCase())).collect(Collectors.toList());
        System.out.println(pokemons);
        if(pokemons.isEmpty()){
            System.out.println("tomt");
            pokemonConsumerService.searchPokemonByAbilitiesAndTypes(ability,type);
        }

        return pokemons;
    }
}
