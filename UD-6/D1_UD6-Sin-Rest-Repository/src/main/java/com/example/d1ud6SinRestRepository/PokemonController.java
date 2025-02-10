package com.example.d1ud6SinRestRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private PokemonRepository pokemonRepository;

    // Obtener todos los Pokémon
    @GetMapping
    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    // Obtener un Pokémon por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        return pokemon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar Pokémon por nombre
    @GetMapping("/search")
    public List<Pokemon> getPokemonByNombre(@RequestParam String nombre) {
        return pokemonRepository.findByNombre(nombre);
    }

    // Crear un nuevo Pokémon
    @PostMapping
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    // Actualizar un Pokémon
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable Long id, @RequestBody Pokemon newPokemon) {
        return pokemonRepository.findById(id)
                .map(pokemon -> {
                    pokemon.setNombre(newPokemon.getNombre());
                    pokemon.setTipo(newPokemon.getTipo());
                    pokemon.setDescripcion(newPokemon.getDescripcion());
                    return ResponseEntity.ok(pokemonRepository.save(pokemon));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un Pokémon
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

