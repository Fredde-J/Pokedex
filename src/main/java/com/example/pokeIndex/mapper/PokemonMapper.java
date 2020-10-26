package com.example.pokeIndex.mapper;


import com.example.pokeIndex.dto.PokemonDto;
import com.example.pokeIndex.entities.Pokemon;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonMapper {
    Pokemon pokemonDtoToPokemon(PokemonDto pokemonDto);
    PokemonDto pokemonToPokemonDto (Pokemon pokemon);
}
