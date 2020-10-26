package com.example.pokeIndex.services;

import com.example.pokeIndex.dto.PokemonDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ConfigurationProperties(prefix = "example.pokemon", ignoreUnknownFields = false)
public class PokemonConsumerService {
    private final RestTemplate restTemplate;
    private String url;

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonDto searchPokemon(String name){
        var urlWithName = url + name;
        System.out.println(urlWithName);
        var pokemon = restTemplate.getForObject(urlWithName, PokemonDto.class);
        if(pokemon==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no pokemon found!");
        }
        return pokemon;
    };
    public void setUrl(String url){
        this.url = url;
    }
}
