package com.example.pokeIndex.repositories;

import com.example.pokeIndex.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonsRepo extends MongoRepository<Pokemon, String> {
    @Query("{'name' : {$regex : ?0}}")
    List<Pokemon> findByNameRegexQuery(String PokemonName);

    @Query("{ 'abilities.ability.name': ?0, 'types.type.name': ?1 }")
    List<Pokemon> findByAbilityType(String ability, String type);

    @Query("{ 'abilities.ability.name': ?0, 'types.type.name': ?1,'height': ?2,'weight': ?3 }")
    List<Pokemon> findByAbilityTypeHeightWeight(String ability, String type, int height, int Weight);
}
