package com.example.formularioAvanzado;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Joke(Long id, String type, String setup, String punchline) { }
