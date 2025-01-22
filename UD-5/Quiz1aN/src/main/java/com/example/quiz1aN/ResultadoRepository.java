package com.example.quiz1aN;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ResultadoRepository extends CrudRepository<Resultado, Long> {
    // Encuentra los resultados de un usuario ordenados por puntuación de mayor a menor
    List<Resultado> findByUsuarioOrderByPuntuacionDesc(Usuario usuario);
}
