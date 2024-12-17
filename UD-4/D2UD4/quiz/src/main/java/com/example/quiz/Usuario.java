package com.example.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Usuario {
	
	@Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
	@Size(max = 12, message = "El nombre no puede tener más de 12 caracteres")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

	@Size(min = 5, message = "El apellido debe tener al menos 5 caracteres")
	@Size(max = 15, message = "El apellido no puede tener más de 15 caracteres")
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

	@Size(min = 4, message = "La descripción debe tener al menos 4 caracteres")
	@Size(max = 20, message = "La descripción no puede tener más de 20 caracteres")
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}