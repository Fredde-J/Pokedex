package com.example.pokeIndex.repositories;

import com.example.pokeIndex.entities.PokemonName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonNameRepo extends MongoRepository<PokemonName, String> {
    @Query("{'names.name' : {$regex : ?0}}")
    public List<String> findByNameRegexQuery(String PokemonName);
}
