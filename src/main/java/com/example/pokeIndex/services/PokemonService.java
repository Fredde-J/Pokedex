package com.example.pokeIndex.services;

import com.example.pokeIndex.dto.PokemonDto;
import com.example.pokeIndex.entities.Pokemon;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ConfigurationProperties(prefix = "example.pokemon", ignoreUnknownFields = false)
public class PokemonService {
    private RestTemplate restTemplate;
    private String url;

    public PokemonService(RestTemplateBuilder restTemplateBuilder){this.restTemplate = restTemplateBuilder.build();}

    public PokemonDto findPokemonByName(String name){
        var urlWithNameQuery = url + name;
        System.out.println(urlWithNameQuery);
        var pokemon = restTemplate.getForObject(urlWithNameQuery, PokemonDto.class);

        if(pokemon != null){
            System.out.println("pokemon: "+ pokemon.getName());
        }else {
            System.out.println("pokemon not found!!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no movie found!");
        }
        return pokemon;

    };
    public void setUrl(String url){
        this.url = url;
    }
}
