package com.luis.luis_books.dto;


import java.util.List;

public record PokemonListDTO(
        Integer count,
        String next,
        String previous,
        List<PokemonSummary> results
) {
    public record PokemonSummary(
            String name,
            String url
    ){}

}
