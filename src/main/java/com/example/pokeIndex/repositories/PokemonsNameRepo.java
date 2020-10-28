package com.example.pokeIndex.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PokemonsNameRepo  {
    private static final String databaseName = "pokeDex";
    private static final String collectionName = "pokemonNames";

    @Autowired
    private MongoClient client;

    public void getAllNames(){
        List<Object> allNames = new ArrayList<>();
        MongoCollection<Document> data = client.getDatabase(databaseName).getCollection(collectionName);
        data.find(new BasicDBObject("names.name", "charizard"));
        System.out.println("all pokemons");
        System.out.println(data.find());

    }
    public void getname(String name){
        MongoOperations mongoOperations = new MongoTemplate(MongoClients.create(), "pokeDex");

    }
}
