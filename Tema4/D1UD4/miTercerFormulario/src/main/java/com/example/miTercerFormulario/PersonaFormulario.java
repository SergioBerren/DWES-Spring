package com.example.miTercerFormulario;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonaFormulario {

	@NotNull
	@Size(min=2, max=30)
	private String nombre;

	@NotNull
	@Min(18)
	private Integer edad;

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String toString() {
		return "Person(Nombre: " + this.nombre + ", Edad: " + this.edad + ")";
	}
}