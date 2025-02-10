package goya.daw2.ud5parte2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Puntuacion {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	int puntos;

	@ManyToOne
	Usuario usuario;
	
	public Puntuacion() {
		super();
	}

	
	public Puntuacion(int puntos) {
		super();
		this.puntos = puntos;
	}

	public Puntuacion(int puntos, Usuario usuario) {
		super();
		this.puntos = puntos;
		this.usuario = usuario;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override public String toString() {
		return puntos+"";
	}

}
