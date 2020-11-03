package com.example.pokeIndex.mapper;

import com.example.pokeIndex.dto.PokemonAbilityDto;
import com.example.pokeIndex.entities.PokemonAbility;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonAbilityMapper {
    PokemonAbility pokemonAbilityDtoToPokemonAbility(PokemonAbilityDto pokemonAbilityDtoDto);
    PokemonAbilityDto pokemonAbilityToPokemonAbilityDto (PokemonAbility pokemonAbility);
}
