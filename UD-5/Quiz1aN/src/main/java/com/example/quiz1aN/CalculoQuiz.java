package com.example.quiz1aN;

import org.springframework.stereotype.Component;

@Component
public class CalculoQuiz {

    public String calcularElemento(int puntuacion) {
        if (puntuacion <= 30) {
            return "Tierra";
        } else if (puntuacion > 30 && puntuacion <= 40) {
            return "Aire";
        } else if (puntuacion > 40 && puntuacion <= 50) {
            return "Agua";
        } else {
            return "Fuego";
        }
    }
}