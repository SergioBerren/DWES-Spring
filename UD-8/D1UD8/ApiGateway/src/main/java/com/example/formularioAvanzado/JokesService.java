package com.example.formularioAvanzado;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokesService {
    private final RestTemplate restTemplate;

    public JokesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchJokes() {
        String apiUrl = "https://official-joke-api.appspot.com/jokes/programming/ten";
        return restTemplate.getForObject(apiUrl, String.class);
    }
}

