package com.luis.luis_books.restController;

import com.luis.luis_books.dto.PokemonDetailDTO;
import com.luis.luis_books.dto.PokemonListDTO;
import com.luis.luis_books.service.impl.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;

    // GET /v1/pokemon?limit=10&offset=0
    @GetMapping
    public ResponseEntity<PokemonListDTO> findAll(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "10") int offset
    ){
        if(limit < 1 || limit > 100 || offset < 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "limit debe estar entre 1 y 100; offset debe ser >= 0"
            );
        }

        return ResponseEntity.ok(
                pokemonService.findAll(limit,offset)
        );
    }

    // GET /v1/pokemon/pikachu
    // GET /v1/pokemon/25
    @GetMapping("/{nameOrId}")
    public ResponseEntity<PokemonDetailDTO> findByNameOrId(
            @PathVariable String nameOrId
    ){
        return ResponseEntity.ok(
                pokemonService.findByNameOrId(nameOrId)
        );
    }
}
