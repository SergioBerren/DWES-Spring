package com.example.saludo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaludoController {
	@GetMapping("/saludo")
	public String saludo(@RequestParam(name="nombre", required=false, defaultValue = "Mundo") String nombre, Model modelo) {
	// nombre = "Sergio";
	modelo.addAttribute("name", nombre);
	return "saludo";
	}
}