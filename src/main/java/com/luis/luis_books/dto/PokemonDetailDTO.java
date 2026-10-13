package com.luis.luis_books.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// @JsonIgnoreProperties(ignoreUnknown = true)
// permite que Jackson ignore propiedades adicionales
// que devuelve PokéAPI y que nosotros no necesitamos mapear.
@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonDetailDTO(
        Integer id,
        String name,
        Integer height,
        Integer weight,
        List<PokemonTypeSlot> types,
        PokemonSprites sprites
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PokemonTypeSlot(
            Integer slot,
            PokemonType type
    ){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PokemonType(
            String name
    ){}

    // @JsonProperty("front_default") indica que el campo JSON
    // front_default corresponde al componente Java frontDefault.
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PokemonSprites(
        @JsonProperty("front_default")
        String frontDefault
    ){}
}
