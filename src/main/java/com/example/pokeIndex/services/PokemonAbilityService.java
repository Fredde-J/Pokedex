package com.example.pokeIndex.services;

import com.example.pokeIndex.dto.PokemonAbilityDto;
import com.example.pokeIndex.entities.PokemonAbility;
import com.example.pokeIndex.mapper.PokemonAbilityMapper;
import com.example.pokeIndex.repositories.PokemonAbilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonAbilityService {

    @Autowired
    PokemonAbilityRepo pokemonAbilityRepo;
    @Autowired
    PokemonConsumerService pokemonConsumerService;
    @Autowired
    PokemonAbilityMapper PokemonAbilityMapper;


    public List<PokemonAbility> findAllByName(String abilityName) {
        abilityName = abilityName.toLowerCase();

        if (abilityName == null) {
            return pokemonAbilityRepo.findAll();
        }

        List<PokemonAbility> pokemonAbilities = new ArrayList<>();
        pokemonAbilities.add(pokemonAbilityRepo.findByName(abilityName));

        if (pokemonAbilities.isEmpty() || pokemonAbilities.get(0) == null) {
            pokemonAbilities.clear();
            PokemonAbilityDto pokemonAbilityDto;
            try {
                pokemonAbilityDto = pokemonConsumerService.searchAbility(abilityName);
            } catch (Exception e) {
                System.out.println(e);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("no ability found with name" + abilityName));
            }

            var pokemonAbility = PokemonAbilityMapper.pokemonAbilityDtoToPokemonAbility(pokemonAbilityDto);
            pokemonAbilities.add(pokemonAbility);
            pokemonAbilityRepo.save(pokemonAbility);
        }

        return pokemonAbilities;
    }

    public PokemonAbility findById(String id) {
        return pokemonAbilityRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the ability by id %s.", id)));

    }

    public PokemonAbility save(PokemonAbility pokemonAbility) {
        return pokemonAbilityRepo.save(pokemonAbility);
    }

    public void update(String id, PokemonAbility pokemonAbility) {
        if (!pokemonAbilityRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("could not find ability by id:" + id));
        }
        pokemonAbility.setId(id);
        pokemonAbilityRepo.save(pokemonAbility);
    }

    public void delete(String id) {
        if (!pokemonAbilityRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, String.format("could not find ability by id" + id));
        }
        pokemonAbilityRepo.deleteById(id);
    }
}
