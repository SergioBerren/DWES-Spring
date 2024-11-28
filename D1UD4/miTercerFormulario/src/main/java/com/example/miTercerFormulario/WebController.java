package com.example.miTercerFormulario;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class WebController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registro) {
		registro.addViewController("/resultado").setViewName("resultado");
	}

	@GetMapping("/")
	public String mostrarFormulario(PersonaFormulario personaFormulario) {
		return "formulario";
	}

	@PostMapping("/")
	public String checkInformacionPersona(@Valid PersonaFormulario personaFormulario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "formulario";
		}

		return "redirect:/resultado";
	}
}