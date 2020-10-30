package com.example.pokeIndex.services;

import com.example.pokeIndex.dto.PokemonDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties(prefix = "example.pokemon", ignoreUnknownFields = false)
public class PokemonConsumerService {
    private final RestTemplate restTemplate;
    private String url;

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<PokemonDto> searchPokemons(List<Object> pokemonNames) {
        List<PokemonDto> pokemonDtos = new ArrayList<>();

        for (int i = 0; i <pokemonNames.size() ; i++) {
            Map<String, Object> map = (Map<String, Object>) pokemonNames.get(i);
            for (String key : map.keySet()) {
                if(key.equals("url")){
                    String urlWithName = map.get(key).toString();
                    System.out.println(urlWithName);
                    pokemonDtos.add(restTemplate.getForObject(urlWithName, PokemonDto.class));
                }
                // String urlWithName = url+"pokemon/"+ map.get(key).toString();
                //
            }
        }
        return pokemonDtos;
    };

    public PokemonDto searchPokemonByAbilitiesAndTypes(String ability, String type){
        var urlWithAbility = url +"ability/"+ ability;
        var urlWithType = url +"type/"+ type;
        System.out.println(urlWithAbility);
        System.out.println(urlWithType);
        var pokemon = restTemplate.getForObject(urlWithType, PokemonDto.class);
        if(pokemon==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no pokemon found!");
        }
        return pokemon;
    };
    public void setUrl(String url){
        this.url = url;
    }
}
