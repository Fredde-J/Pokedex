package com.example.pokeIndex.mapper;

import com.example.pokeIndex.dto.PokemonNameDto;
import com.example.pokeIndex.entities.PokemonName;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonNameMapper {
    PokemonName pokemonNameDtoToPokemonName(PokemonNameDto pokemonNameDto);
    PokemonNameDto pokemonNameToPokemonNameDto (PokemonName pokemonName);
}
