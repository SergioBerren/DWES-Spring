package com.example.quiz1aN;

import jakarta.persistence.*;

@Entity
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String elemento;
    private int puntuacion;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)  // Relación con Usuario
    private Usuario usuario;

    // Constructor sin parámetros, necesario para Hibernate
    public Resultado() {}

    // Constructor con parámetros (ya existente)
    public Resultado(String elemento, int puntuacion, String descripcion, Usuario usuario) {
        this.elemento = elemento;
        this.puntuacion = puntuacion;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
