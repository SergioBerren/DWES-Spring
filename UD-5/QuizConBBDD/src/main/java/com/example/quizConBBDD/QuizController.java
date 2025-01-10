package com.example.quizConBBDD;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class QuizController {

    @Autowired
    private CalculoQuiz calculoQuiz;

    @Autowired
    private ResultadoRepository resultadoRepository;

    @GetMapping("/")
    public String mostrarInicio(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        return "index";
    }

    @PostMapping("/inicio")
    public String comenzarQuiz(@ModelAttribute @Valid Usuario usuario, BindingResult result, HttpSession sesion, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("usuario", usuario);
            return "index";
        }

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

        if (respuesta == null) {
            modelo.addAttribute("error", "Debes seleccionar una respuesta antes de continuar.");
            modelo.addAttribute("numeroPregunta", numeroPregunta);
            return "pregunta" + numeroPregunta;
        }

        // Actualizar la puntuación en la sesión
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
            return "redirect:/";
        }

        int puntuacion = (int) sesion.getAttribute("puntuacion");
        String elemento = calculoQuiz.determinarElemento(puntuacion);

        // Guardar el resultado en la base de datos
        Resultado resultado = new Resultado(usuario.getNombre(), elemento, puntuacion);
        resultadoRepository.save(resultado);

        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("puntuacion", puntuacion);
        modelo.addAttribute("elemento", elemento.toLowerCase());
        modelo.addAttribute("descripcion", usuario.getDescripcion());

        return "resultado";
    }
}