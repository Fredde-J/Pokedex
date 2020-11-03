package com.example.pokeIndex.services;

import com.example.pokeIndex.dto.PokemonAbilityDto;
import com.example.pokeIndex.dto.PokemonDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
                    pokemonDtos.add(restTemplate.getForObject(urlWithName, PokemonDto.class));
                }
            }
        }
        return pokemonDtos;
    };

    public PokemonAbilityDto searchAbility(String abilityName) {
        String urlWithName = url+"/ability/"+abilityName;
        var pokemonAbilityDto = restTemplate.getForObject(urlWithName, PokemonAbilityDto.class);
        return pokemonAbilityDto;
    };


}
