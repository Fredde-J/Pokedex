package com.example.pokeIndex.services;

import com.example.pokeIndex.dto.PokemonDto;
import com.example.pokeIndex.entities.Pokemon;
import com.example.pokeIndex.entities.PokemonAbility;
import com.example.pokeIndex.entities.PokemonName;
import com.example.pokeIndex.mapper.PokemonAbilityMapper;
import com.example.pokeIndex.repositories.PokemonAbilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonAbilityService {

    @Autowired
    PokemonAbilityRepo pokemonAbilityRepo;
    @Autowired
    PokemonConsumerService pokemonConsumerService;
    @Autowired
    PokemonAbilityMapper PokemonAbilityMapper;

    public List<PokemonAbility> findAllByName(String abilityName) {
        if (abilityName == null) {
            return pokemonAbilityRepo.findAll();
        }
        if (abilityName.length() < 3) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("You need at least 3 letters to search pokemons abilities by name"));
        }
        PokemonAbility pokemonAbility = new PokemonAbility();
        try {
            pokemonAbility = pokemonAbilityRepo.findByName(abilityName);
        } catch (Exception e) {
            System.out.println(e);
        }
        if(pokemonAbility==null){
            var pokemonsAbilityDto = pokemonConsumerService.searchAbility(abilityName);
            if(pokemonsAbilityDto==null){
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("ability with name "+abilityName+" not found"));
            }
            pokemonAbility = PokemonAbilityMapper.pokemonAbilityDtoToPokemonAbility(pokemonsAbilityDto);
            pokemonAbilityRepo.save(pokemonAbility);
        }

        return pokemonAbility;
    }
}
