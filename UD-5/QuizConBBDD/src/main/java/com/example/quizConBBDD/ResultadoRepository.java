package com.example.quizConBBDD;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    List<Resultado> findByNombre(String nombre);
}
