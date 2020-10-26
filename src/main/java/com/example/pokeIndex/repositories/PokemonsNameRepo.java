package com.example.pokeIndex.repositories;

import com.example.pokeIndex.entities.Pokemon;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PokemonsNameRepo  {
    private static final String databaseName = "pokeDex";
    private static final String collectionName = "pokemonNames";

    @Autowired
    private MongoClient client;

    public void GetAllNames(){
        List<Object> allNames = new ArrayList<>();
        MongoCollection<Document> data = client.getDatabase(databaseName).getCollection(collectionName);
        data.find().map(Document::toJson).forEach(allNames::add);
        System.out.println("all pokemons");
        Object names = allNames.get(0);
        System.out.println(names);
    }
    public void Getname(){
        List<Object> allNames = new ArrayList<>();
        MongoCollection<Document> data = client.getDatabase(databaseName).getCollection(collectionName);
        data.find().map(Document::toJson).forEach(allNames::add);
        System.out.println("all pokemons");
        System.out.println(allNames.get(2));
    }
}
