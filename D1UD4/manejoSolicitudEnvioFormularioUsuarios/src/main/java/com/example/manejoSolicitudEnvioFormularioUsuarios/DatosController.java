package com.example.manejoSolicitudEnvioFormularioUsuarios;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DatosController {

  @GetMapping("/formulario")
  public String datosFormulario(Model modelo) {
    modelo.addAttribute("formulario", new Datos());
    return "formulario";
  }

  @PostMapping("/formulario")
  public String datosEnviados(@ModelAttribute Datos datos, Model modelo) {
    modelo.addAttribute("formulario", datos);
    return "resultado";
  }
}