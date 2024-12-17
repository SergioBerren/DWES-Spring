package com.example.quiz;

import org.springframework.stereotype.Service;

@Service
public class CalculoQuiz {

    public String determinarElemento(int puntuacion) {
        if (puntuacion <= 30) {
            return "Tierra";
        } else if (puntuacion <= 40) {
            return "Aire";
        } else if (puntuacion <= 50) {
            return "Agua";
        } else {
            return "Fuego";
        }
    }
}