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

    @Query("{'abilities.ability.name': ?0}")
    List<Pokemon> findByAbility(String ability);

    @Query("{'types.type.name': ?0}")
    List<Pokemon> findByType(String type);

    @Query("{'weight': ?0}")
    List<Pokemon> findByWeight(Integer weight);

    @Query("{'name':{$regex : ?0},'abilities.ability.name': ?1, 'types.type.name': ?2,'weight': ?3 }")
    List<Pokemon> findByNameAbilityTypeWeight(String name,String ability, String type, int Weight);
}
