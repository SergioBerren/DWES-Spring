package com.example.quiz1aN;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {

    @Autowired
    private CalculoQuiz calculoQuiz;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

        int puntuacion = (int) sesion.getAttribute("puntuacion");
        sesion.setAttribute("puntuacion", puntuacion + respuesta);

        if (numeroPregunta < 15) {
            return "redirect:/pregunta/" + (numeroPregunta + 1);
        } else {
            return "redirect:/resultado";
        }
    }

    @GetMapping("/resultado")
    public String mostrarResultado(HttpSession sesion, Model modelo) {
        Usuario usuarioSesion = (Usuario) sesion.getAttribute("usuario");
        int puntuacionTotal = (int) sesion.getAttribute("puntuacion");

        // Guardar o actualizar usuario
        Optional<Usuario> usuarioExistente = usuarioRepository.findByNombre(usuarioSesion.getNombre());
        Usuario usuario;
        if (usuarioExistente.isPresent()) {
            usuario = usuarioExistente.get();
        } else {
            usuario = new Usuario(usuarioSesion.getNombre(), usuarioSesion.getApellido(), usuarioSesion.getDescripcion());
            usuarioRepository.save(usuario);
        }

        // Calcular el "elemento" basado en la puntuación total
        String elemento = calculoQuiz.calcularElemento(puntuacionTotal);  // Asumimos que 'calculoQuiz' hace este cálculo
        String descripcion = usuario.getDescripcion();  // O alguna lógica para obtener la descripción

        // Crear el objeto Resultado con valores reales
        Resultado resultado = new Resultado(elemento, puntuacionTotal, descripcion, usuario);

        // Obtener las puntuaciones del jugador, limitadas a las 5 más altas
        List<Resultado> resultadosJugador = resultadoRepository.findByUsuarioOrderByPuntuacionDesc(usuario);
        
        // Mantener solo las 5 puntuaciones más altas
        if (resultadosJugador.size() >= 5) {
            // Eliminar la puntuación más baja si ya hay 5 o más resultados
            Resultado puntuacionBaja = resultadosJugador.get(resultadosJugador.size() - 1);
            resultadoRepository.delete(puntuacionBaja);  // Eliminar la puntuación más baja
        }

        // Guardar el nuevo resultado
        resultadoRepository.save(resultado);

        // Añadir el resultado y usuario al modelo
        modelo.addAttribute("resultado", resultado);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("puntuacion", puntuacionTotal);

        return "resultado";  // Devuelve la vista 'resultado.html'
    }

    // Ruta para mostrar los resultados de la base de datos
    @GetMapping("/bbdd")
    public String mostrarBbdd(Model modelo) {
        // Obtener todos los resultados de la base de datos
        Iterable<Resultado> resultados = resultadoRepository.findAll();

        // Pasar los resultados al modelo
        modelo.addAttribute("resultados", resultados);

        // Devolver la vista bbdd.html
        return "bbdd";  
    }
}
