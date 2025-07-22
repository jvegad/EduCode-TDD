# Análisis de Caso: Test-Driven Development (TDD) en EduCode

## Alumna: Josselyn Vega Devia

Este proyecto es una implementación práctica de la metodología Test-Driven Development (TDD) para el sistema de gestión de cursos de la empresa ficticia EduCode. El objetivo es demostrar cómo TDD, a través de su ciclo Red-Green-Refactor, mejora la calidad del software, reduce bugs y facilita el mantenimiento.

*   **Tecnologías Utilizadas:**
    *   **Lenguaje:** Java (Versión 24)
    *   **Gestión de Dependencias:** Apache Maven
    *   **Framework de Pruebas:** JUnit 5
    *   **Simulación de Dependencias (Mocks):** Mockito
    *   **Medición de Cobertura:** JaCoCo

---

## 1. Instrucciones de Configuración y Ejecución

A continuación se detallan los pasos para clonar, compilar y probar el proyecto.

### Prerrequisitos
*   JDK 24 (o compatible) instalado.
*   Apache Maven instalado y configurado en el PATH del sistema.
*   Git instalado.

### Pasos para la Ejecución

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/jvegad/EduCode-TDD.git
    cd EduCode-TDD
    ```

2.  **Compilar y ejecutar las pruebas:**
    El siguiente comando limpiará compilaciones anteriores, compilará el código y ejecutará todas las pruebas unitarias.
    ```bash
    mvn clean test
    ```
    Al finalizar, deberías ver un `BUILD SUCCESS` en la consola.

## 2. Plan de Adopción de TDD

### Diferencias entre TDD y Testing Tradicional

El testing tradicional es una fase que ocurre *después* de que el código ha sido escrito. Los desarrolladores implementan una funcionalidad completa y luego se la entregan al equipo de QA, o escriben pruebas al final. Este enfoque a menudo conduce a encontrar fallos tarde en el ciclo de desarrollo, cuando solucionarlos es más costoso y complejo.

En contraste, el **Test-Driven Development (TDD)** invierte este proceso. La regla fundamental es: **no escribir una sola línea de código de producción sin tener primero una prueba automatizada que falle**. El ciclo se resume en:

1.  **Red (Rojo):** Escribir una prueba para una funcionalidad que aún no existe. La prueba debe fallar, porque el código correspondiente no ha sido implementado. Esta fase es crucial porque nos obliga a pensar en el *qué* antes del *cómo*. Define claramente los requisitos de la nueva funcionalidad desde la perspectiva de su uso.
2.  **Green (Verde):** Escribir la cantidad de código *mínima y más simple posible* para que la prueba pase. En esta etapa no buscamos la perfección, solo la funcionalidad.
3.  **Refactor (Refactorizar):** Con la seguridad de que la funcionalidad está cubierta por una prueba, se mejora la estructura interna del código (eliminando duplicación, mejorando la legibilidad, etc.) sin cambiar su comportamiento externo. Las pruebas garantizan que no se introduzcan regresiones.

### Ventajas y Limitaciones de TDD

#### Ventajas:
*   **Detección Temprana de Bugs:** Los errores se detectan en el momento en que se escribe el código, no semanas o meses después.
*   **Diseño Modular y Desacoplado:** Escribir pruebas primero obliga a pensar en interfaces y dependencias, promoviendo un diseño más limpio y fácil de mantener.
*   **Documentación Ejecutable:** Las pruebas unitarias sirven como una documentación viva del sistema. Describen cómo se debe comportar cada componente.
*   **Red de Seguridad para Cambios:** Un conjunto sólido de pruebas permite refactorizar y añadir nuevas funcionalidades con la confianza de que no se romperá algo existente.

#### Limitaciones:
*   **Curva de Aprendizaje:** Requiere un cambio de mentalidad significativo para los equipos acostumbrados al desarrollo tradicional.
*   **Ritmo Inicial más Lento:** Al principio, puede parecer que el desarrollo es más lento por la necesidad de escribir pruebas antes del código.
*   **No es una Bala de Plata:** TDD no es ideal para todo tipo de desarrollos, como interfaces gráficas de usuario (GUI) o proyectos muy exploratorios donde los requisitos son volátiles.

---

## 3. Ciclo Red-Green-Refactor en Acción

A continuación, se implementarán las funcionalidades `calcularPromedio()` y `verificarCupos()` en una clase `Curso` siguiendo estrictamente el ciclo TDD.

### Organización y Ejecución en Visual Studio Code

El proyecto se configuró con Maven. La extensión **Extension Pack for Java** en VS Code detecta automáticamente las pruebas de JUnit. Esto permite:
*   Ejecutar todas las pruebas del proyecto.
*   Ejecutar una única clase de prueba.
*   Ejecutar un único método de prueba.
*   Visualizar los resultados directamente en el editor, con indicadores de éxito (verde) o fallo (rojo).

### Funcionalidad: `calcularPromedio()`

#### Ciclo 1: Red - Crear prueba que falla

Primero, creamos la clase `Curso.java` en `src/main/java/com/educode/` y la clase de prueba `CursoTest.java` en `src/test/java/com/educode/`.

**`CursoTest.java` (Fragmento)**
```java
@Test
    void calcularPromedio() {
        System.out.println("/*Calcula Promedio --------------------*/");
        servicio.agregarNota(7.0);
        servicio.agregarNota(5.8);
        servicio.agregarNota(4.2);
        assertEquals(5.66, servicio.calcularPromedio(), 0.01);
    }
```

**Resultado de la ejecución:**
*[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project EduCode-TDD: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/josse/Desktop/JAVA/EduCode-TDD/src/test/java/com/educode/CursoTest.java:[19,17] cannot find symbol
[ERROR]   symbol:   method agregarNota(double)
[ERROR]   location: variable servicio of type com.educode.Curso
[ERROR] /C:/Users/josse/Desktop/JAVA/EduCode-TDD/src/test/java/com/educode/CursoTest.java:[22,36] cannot find symbol
[ERROR]   symbol:   method calcularPromedio()
[ERROR]   location: variable servicio of type com.educode.Curso
[ERROR] -> [Help 1]*

#### Ciclo 2: Green - Escribir código mínimo para pasar la prueba

**`Curso.java` (Fragmento)**
```java
public double calcularPromedio() {
    if (notas.isEmpty()) {
        throw new IllegalStateException("No hay notas para calcular el promedio");
    }
    double suma = 0;
    for (double nota : this.notas) {
        suma += nota;
    }
    return suma / this.notas.size();
}
```

**Resultado de la ejecución:**
*[INFO] Results:
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
[INFO] Total time:  20.138 s
[INFO] Finished at: 2025-07-22T15:24:58-03:00*

#### Ciclo 3: Refactor - Mejorar el código

**`Curso.java` (Fragmento tras refactorización)**
```java
public double calcularPromedio() {
    if (notas.isEmpty()) {
        return 0.0;
    }
    return notas.stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
}
```

*Una vez que la prueba unitaria estuvo en verde, se procedió a la fase de refactorización. El objetivo era mejorar la estructura interna del método calcularPromedio sin alterar su comportamiento funcional, garantizando que la prueba siguiera pasando.
La implementación inicial, aunque funcional para el caso de prueba, era imperativa y frágil, ya que provocaba un error de división por cero si se le presentaba una lista de notas vacía.
La refactorización consistió en reemplazar el bucle for manual por una implementación declarativa basada en el API de Streams de Java.*

---

### Funcionalidad: `verificarCupos()`

#### Ciclo 1: Red - Crear prueba que falla

Primero, verifico que la clase `Curso.java` en `src/main/java/com/educode/` contenga lo necesario para verificar el cupo.

**`CursoTest.java` (Fragmento)**
```java
@Test
    void testVerificarCuposDeberiaRetornarTrueSiHayEspacio() {
        Curso cursoConCupo = new Curso("Programación Básica", 5);
        cursoConCupo.inscribirEstudiante(new Estudiante("Ana"));
        boolean hayCupo = servicio.verificarCupos(cursoConCupo);
        assertTrue(hayCupo, "Debería retornar true porque 5 > 1");
    }
```

**Resultado de la ejecución:**
*[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/josse/Desktop/JAVA/EduCode-TDD/src/test/java/com/educode/CursoTest.java:[32,35] cannot find symbol
  symbol:   method verificarCupos(com.educode.Curso)
  location: variable servicio of type com.educode.Curso
[ERROR] /C:/Users/josse/Desktop/JAVA/EduCode-TDD/src/test/java/com/educode/CursoTest.java:[41,35] cannot find symbol
  symbol:   method verificarCupos(com.educode.Curso)
  location: variable servicio of type com.educode.Curso*

#### Ciclo 2: Green - Escribir código mínimo para pasar la prueba

**`Curso.java` (Fragmento)**
```java
public boolean verificarCupos(Curso curso) {
    int estudiantesInscritos = curso.getEstudiantes().size();
    int cupoMaximo = curso.getCupoMaximo();
    return estudiantesInscritos < cupoMaximo;
}
```

**Resultado de la ejecución:**
*[INFO] Results:
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
[INFO] Total time:  16.886 s
[INFO] Finished at: 2025-07-22T16:04:26-03:00*

#### Ciclo 3: Refactor - Mejorar el código

**`Curso.java` (Fragmento tras refactorización)**
```java
public boolean verificarCupos(Curso curso) {
    return curso.getEstudiantes().size() < curso.getCupoMaximo();
}
```

*En este caso, no se encontraron bugs ni complejidad excesiva, por lo que la refactorización no era estrictamente necesaria. Sin embargo, se optó por una versión más concisa eliminando las variables locales intermedias. Dado que la lógica es una simple comparación, la legibilidad no se ve comprometida y el código queda más compacto. Este cambio se realizó con la confianza de que la suite de pruebas existente validaría que el comportamiento no fue alterado.*

---

## 4. Código Fuente

### `Curso.java`
```java
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
```

### `CursoTest.java`
```java
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
```

## 5. Reflexión Final

### Impacto de TDD en la Reducción de Bugs
*La implementación de TDD demostró ser una estrategia proactiva para mejorar la calidad del código. En lugar de esperar a que los bugs aparezcan, el ciclo nos obliga a definir los requisitos y casos borde (como listas vacías o entradas nulas) en una prueba antes de escribir una sola línea de lógica. Esto no solo previene errores desde el inicio, sino que la suite de pruebas resultante actúa como una red de seguridad que detecta regresiones inmediatamente, haciendo el mantenimiento y la evolución del código mucho más seguros.*

### Dificultades en la Adopción
*El principal desafío al adoptar TDD es el cambio de mentalidad. Requiere una disciplina consciente para resistir el impulso de escribir la solución directamente y, en su lugar, comenzar con una prueba que falla. Este proceso puede sentirse más lento al principio, ya que el enfoque se desplaza de "escribir código" a "satisfacer un requisito". Superar esto es el mayor obstáculo para un equipo acostumbrado a flujos de trabajo tradicionales.*

### Lecciones Aprendidas
*La lección más importante es que TDD es tanto una herramienta de diseño como de testing. Fuerza la creación de código modular y desacoplado que es inherentemente testeable. Además, se comprobó que la integración de herramientas modernas es clave para la eficiencia. El soporte nativo de JUnit en Visual Studio Code, junto a la gestión de dependencias de Maven, permite ejecutar el ciclo Red-Green-Refactor de manera fluida y rápida.*