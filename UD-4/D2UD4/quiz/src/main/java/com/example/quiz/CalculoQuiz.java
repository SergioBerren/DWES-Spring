package com.example.quiz;

import org.springframework.stereotype.Service;

@Service
public class CalculoQuiz {

    public String determinarElemento(int puntuacion) {
        Elemento elemento = Elemento.obtenerElementoPorPuntuacion(puntuacion);
        return elemento.name();  // Devolver el nombre del elemento como cadena
    }
}
