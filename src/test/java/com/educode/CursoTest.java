package com.educode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CursoTest {

    Curso servicio;

    @BeforeEach
    void setUp() {
        servicio = new Curso();
    }

    @Test
    void calcularPromedio() {
        System.out.println("/*Calcula Promedio --------------------*/");
        servicio.agregarNota(7.0);
        servicio.agregarNota(5.8);
        servicio.agregarNota(4.2);
        assertEquals(5.66, servicio.calcularPromedio(), 0.01);
    }

    @Test
    void testVerificarCuposDeberiaRetornarTrueSiHayEspacio() {
        System.out.println("/*Verifica Cupo --------------------*/");
        Curso cursoConCupo = new Curso("Programación Básica", 5);
        cursoConCupo.inscribirEstudiante(new Estudiante("Ana"));
        boolean hayCupo = servicio.verificarCupos(cursoConCupo);
        assertTrue(hayCupo, "Debería retornar true porque 5 > 1");
    }
}
