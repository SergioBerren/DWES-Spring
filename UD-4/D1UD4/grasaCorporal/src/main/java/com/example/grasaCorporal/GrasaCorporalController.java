package com.example.grasaCorporal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GrasaCorporalController {

    // Instancia de la lógica de negocio
    private DatosGrasaCorporal datos = new DatosGrasaCorporal();

    @GetMapping("/")
    public String formulario() {
        return "formulario";
    }

    @PostMapping("/calcular")
    public String calcularGrasa(
            @RequestParam("genero") String genero,
            @RequestParam("imc") double imc,
            @RequestParam("pa") double pa,
            @RequestParam(value = "mc", required = false) Double mc,
            Model model) {
        
        double porcentajeGrasa;
        String clasificacion;

        // Realizar el cálculo según el género
        if ("hombre".equals(genero) && mc != null) {
            porcentajeGrasa = datos.calcularGrasaHombres(mc, pa, imc);
            clasificacion = datos.clasificarHombres(porcentajeGrasa);
        } else {
            porcentajeGrasa = datos.calcularGrasaMujeres(imc, pa);
            clasificacion = datos.clasificarMujeres(porcentajeGrasa);
        }

        // Pasar datos a la vista
        model.addAttribute("porcentajeGrasa", porcentajeGrasa);
        model.addAttribute("clasificacion", clasificacion);

        return "resultado";
    }
}