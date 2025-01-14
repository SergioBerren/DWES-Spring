package com.example.quizConBBDD;

import jakarta.persistence.*;

@Entity
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String elemento;

    @Column(nullable = false)
    private int puntuacion;

    @Column(nullable = false)
    private String descripcion;

    public Resultado() {
    }

    public Resultado(String nombre, String elemento, int puntuacion, String descripcion) {
        this.nombre = nombre;
        this.elemento = elemento;
        this.puntuacion = puntuacion;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", elemento='" + elemento + '\'' +
                ", puntuacion=" + puntuacion +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}