package com.example.pokeIndex.repositories;

import com.example.pokeIndex.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonsRepo extends MongoRepository<Pokemon, String> {
    @Query("{'name' : {$regex : ?0}}")
    public List<Pokemon> getByNameRegexQuery(String PokemonName);
}
