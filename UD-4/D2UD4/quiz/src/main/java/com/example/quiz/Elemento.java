package com.example.quiz;

public enum Elemento {
    TIERRA(30),
    AIRE(40),
    AGUA(50),
    FUEGO(Integer.MAX_VALUE); // Usamos Integer.MAX_VALUE para que Fuego cubra cualquier puntuación mayor a 50

    private final int maxPuntuacion;

    // Constructor para asignar la puntuación máxima a cada elemento
    Elemento(int maxPuntuacion) {
        this.maxPuntuacion = maxPuntuacion;
    }

    // Método para obtener la puntuación máxima
    public int getMaxPuntuacion() {
        return maxPuntuacion;
    }

    // Método estático para obtener el elemento basado en la puntuación
    public static Elemento obtenerElementoPorPuntuacion(int puntuacion) {
        for (Elemento elemento : Elemento.values()) {
            if (puntuacion <= elemento.getMaxPuntuacion()) {
                return elemento;
            }
        }
        return FUEGO;  // Si no se encuentra, se devuelve "FUEGO" por defecto
    }
}
