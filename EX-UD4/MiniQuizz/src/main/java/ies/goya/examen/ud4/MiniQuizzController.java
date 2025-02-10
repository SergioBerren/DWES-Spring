package ies.goya.examen.ud4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MiniQuizzController {

	static final String[] SIGNOS = {"Aries", "Tauro", "Géminis", "Cáncer", "Leo", "Virgo", "Libra", "Escorpio", "Sagitario" };
	static final String[] AFICCIONES = { "Deportes", "Juerga", "Lectura" , "Relaciones sociales" };
	
	// No hace falta entender este método que a partir de unos datos genera una categoría de forma algo random
	static String generaCategoría(String nombre, Integer signo, String aficciones) {
		String[] categorías = {"Tierra","Aire","Agua","Fuego"};
		String[] aficcionesArray = aficciones.split(",");
		Integer num = Arrays.asList(aficcionesArray).stream().map(Integer::parseInt).reduce(0,Integer::sum);
		int indice = (nombre.length() * signo * num) % categorías.length;
		return categorías[indice];
	}
	

	
	
	
}
