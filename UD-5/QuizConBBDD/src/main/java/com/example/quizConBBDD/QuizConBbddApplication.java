package com.example.quizConBBDD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizConBbddApplication {

    private static final Logger log = LoggerFactory.getLogger(QuizConBbddApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QuizConBbddApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ResultadoRepository resultadoRepository) {
        return (args) -> {
            // Guardar algunos resultados de prueba
            resultadoRepository.save(new Resultado("Javi", "fuego", 75));
            resultadoRepository.save(new Resultado("Laila", "agua", 45));
            resultadoRepository.save(new Resultado("Mario", "tierra", 20));
            resultadoRepository.save(new Resultado("Rodri", "aire", 20));
            resultadoRepository.save(new Resultado("Javi", "tierra", 20));

            // Mostrar todos los resultados
            log.info("Resultados encontrados con findAll():");
            log.info("-------------------------------------");
            resultadoRepository.findAll().forEach(resultado -> {
                log.info(resultado.toString());
            });

            // Buscar un resultado por ID
            Resultado resultado = resultadoRepository.findById(1L).orElse(null);
            log.info("Resultado encontrado con findById(1L):");
            log.info("-------------------------------------");
            if (resultado != null) {
                log.info(resultado.toString());
            } else {
                log.info("No se encontró ningún resultado con ID 1.");
            }

            // Buscar resultados por nombre
            log.info("Resultados encontrados con findByNombre('Alice'):");
            log.info("-----------------------------------------------");
            resultadoRepository.findByNombre("Alice").forEach(res -> {
                log.info(res.toString());
            });
        };
    }
}