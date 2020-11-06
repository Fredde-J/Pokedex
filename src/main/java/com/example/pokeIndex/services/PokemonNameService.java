package com.example.pokeIndex.services;
import com.example.pokeIndex.dto.PokemonNameDto;
import com.example.pokeIndex.mapper.PokemonNameMapper;
import com.example.pokeIndex.repositories.PokemonNameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokemonNameService {
    private final RestTemplate restTemplate;
    private String url ="https://pokeapi.co/api/v2/pokemon?offset=0&limit=1050";

    @Autowired
    PokemonNameMapper pokemonNameMapper;
    @Autowired
    PokemonNameRepo pokemonNameRepo;

    public PokemonNameService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //@PostConstruct
    public void getPokemonsNames(){
        PokemonNameDto pokemonNameDto;
        try{
            pokemonNameDto = restTemplate.getForObject(url, PokemonNameDto.class);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no pokemon found!");
        }
        var pokemonName = pokemonNameMapper.pokemonNameDtoToPokemonName(pokemonNameDto);
        pokemonNameRepo.save(pokemonName);
    };
    public void setUrl(String url){
        this.url = url;
    }
}
