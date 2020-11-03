package com.example.pokeIndex.repositories;

import com.example.pokeIndex.entities.PokemonAbility;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonAbilityRepo extends MongoRepository<PokemonAbility, String> {
    @Query("{'name' :  ?0}")
    PokemonAbility findByName(String abilityName);
}
