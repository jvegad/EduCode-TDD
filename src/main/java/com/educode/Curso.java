package com.educode;

import java.util.ArrayList;
import java.util.List;

public class Curso {

    private final List<Double> notas;
    private String nombre;
    private List<Estudiante> estudiantes = new ArrayList<>();
    private int cupoMaximo;

    public Curso() {
        this.notas = new ArrayList<>();
    }

    public Curso(String nombre, int cupoMaximo) {
        this.notas = null;
        this.nombre = nombre;
        this.cupoMaximo = cupoMaximo;
    }

    public void agregarNota(Double nota) {
        if (nota == null || nota < 0 || nota > 7) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 7");
        }
        notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        return notas.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public void inscribirEstudiante(Estudiante estudiante) {
        if (estudiantes.size() < cupoMaximo) {
            estudiantes.add(estudiante);
        }
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public boolean verificarCupos(Curso curso) {
        return curso.getEstudiantes().size() < curso.getCupoMaximo();
    }
}
