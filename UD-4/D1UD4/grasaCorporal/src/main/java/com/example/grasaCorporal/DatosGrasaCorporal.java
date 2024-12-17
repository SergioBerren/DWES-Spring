package com.example.grasaCorporal;

public class DatosGrasaCorporal {

    public double calcularGrasaMujeres(double imc, double pa) {
        return ((imc / pa) * 10) + imc + 10;
    }

    public double calcularGrasaHombres(double mc, double pa, double imc) {
        return ((mc / pa) * 10) + imc;
    }

    public String clasificarHombres(double porcentajeGrasa) {
        if (porcentajeGrasa < 6) {
            return "Esencial";
        } else if (porcentajeGrasa < 14) {
            return "Atleta";
        } else if (porcentajeGrasa < 18) {
            return "Fitness";
        } else if (porcentajeGrasa < 25) {
            return "Aceptable";
        } else {
            return "Obesidad";
        }
    }

    public String clasificarMujeres(double porcentajeGrasa) {
        if (porcentajeGrasa < 14) {
            return "Esencial";
        } else if (porcentajeGrasa < 21) {
            return "Atleta";
        } else if (porcentajeGrasa < 25) {
            return "Fitness";
        } else if (porcentajeGrasa < 32) {
            return "Aceptable";
        } else {
            return "Obesidad";
        }
    }
}