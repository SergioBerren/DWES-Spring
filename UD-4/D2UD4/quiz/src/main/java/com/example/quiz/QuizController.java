package com.example.quiz;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuizController {

    @Autowired
    private CalculoQuiz calculoQuiz;

    @GetMapping("/")
    public String mostrarInicio(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        return "index";
    }

    @PostMapping("/inicio")
    public String comenzarQuiz(@ModelAttribute @Valid Usuario usuario, BindingResult result, HttpSession sesion, Model modelo) {
        // Si hay errores de validación, vuelve al formulario de inicio
        if (result.hasErrors()) {
            modelo.addAttribute("usuario", usuario);
            return "index";  // Regresa al formulario de inicio
        }

        // Si no hay errores, continua con la lógica
        sesion.setAttribute("usuario", usuario);
        sesion.setAttribute("puntuacion", 0);
        return "redirect:/pregunta/1";
    }

    @GetMapping("/pregunta/{numero}")
    public String mostrarPregunta(@PathVariable("numero") int numero, Model modelo) {
        modelo.addAttribute("numeroPregunta", numero);
        return "pregunta" + numero;
    }

    @PostMapping("/enviar")
    public String enviarRespuesta(
            @RequestParam(value = "respuesta", required = false) Integer respuesta,
            @RequestParam("pregunta") int numeroPregunta,
            HttpSession sesion,
            Model modelo) {

        // Validar que la respuesta no sea nula
        if (respuesta == null) {
            modelo.addAttribute("error", "Debes seleccionar una respuesta antes de continuar.");
            modelo.addAttribute("numeroPregunta", numeroPregunta);
            return "pregunta" + numeroPregunta; // Vuelve a la misma página de la pregunta
        }

        // Obtener y actualizar la puntuación
        int puntuacion = (int) sesion.getAttribute("puntuacion");
        sesion.setAttribute("puntuacion", puntuacion + respuesta);

        // Redirigir a la siguiente pregunta o al resultado final
        if (numeroPregunta < 15) {
            return "redirect:/pregunta/" + (numeroPregunta + 1);
        } else {
            return "redirect:/resultado";
        }
    }

    @GetMapping("/resultado")
    public String mostrarResultado(HttpSession sesion, Model modelo) {
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/"; // Redirige al inicio si no hay un usuario
        }
        
        int puntuacion = (int) sesion.getAttribute("puntuacion");
        String elemento = calculoQuiz.determinarElemento(puntuacion);

        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("puntuacion", puntuacion);
        modelo.addAttribute("elemento", elemento.toLowerCase());
        modelo.addAttribute("descripcion", usuario.getDescripcion());
        
        return "resultado";
    }
}
