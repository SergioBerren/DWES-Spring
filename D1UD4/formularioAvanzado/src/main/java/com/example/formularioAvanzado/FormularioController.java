package com.example.formularioAvanzado;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class FormularioController {

    @GetMapping("/formulario")
    public String mostrarFormulario(DatosFormulario datosFormulario) {
        return "formulario";
    }

    @PostMapping("/formulario")
    public String procesarFormulario(@Valid DatosFormulario datosFormulario, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "formulario"; // Si hay errores, se regresa al formulario
        }
        
        // Si no hay errores, se muestran los datos del formulario en una página de resultado
        // modelo.addAttribute("formulario", formulario); // Pasa el formulario validado al modelo
        return "resultado"; // Redirige a la vista "resultado.html"
    }
}