package com.luis.luis_books.service.impl;

import com.luis.luis_books.dto.PokemonDetailDTO;
import com.luis.luis_books.dto.PokemonListDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokemonService {

    private final RestClient restClient;

    public PokemonService(RestClient.Builder builder,
                          @Value("${pokeapi.base-url}") String baseUrl){

        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    // Obtener una lista paginada
    public PokemonListDTO findAll(int limit, int offset){
            try {
                PokemonListDTO response = restClient
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/pokemon")
                                .queryParam("limit",limit)
                                .queryParam("offset", offset)
                                .build()
                        )
                        .retrieve()
                        .body(PokemonListDTO.class);

                if( response == null){
                    throw new ResponseStatusException(
                            HttpStatus.BAD_GATEWAY,
                            "PokeAPI devolvio una respuesta vacia"
                    );
                }

                return response;


            } catch (RestClientException e) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "No se pudo consultar la lista de Pokemon",
                        e
                );
            }
    }

    // Obtener Pokemon por nombre o ID
    public PokemonDetailDTO findByNameOrId(String nameOrId){
        try{
            PokemonDetailDTO response = restClient
                    .get()
                    .uri("/pokemon/{nameOrId}", nameOrId)
                    .retrieve()
                    .body(PokemonDetailDTO.class);

            if(response == null){
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "PokeAPI devolvio una respuesta vacia"
                );
            }

            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Pokemon no encontrado: " + nameOrId,
                    e
            );
        } catch (RestClientException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Error al consultar PokeAPI",
                    ex
            );
        }
    }


}
