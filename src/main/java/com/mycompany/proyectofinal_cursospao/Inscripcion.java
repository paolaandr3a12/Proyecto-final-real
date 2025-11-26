
package com.mycompany.proyectofinal_cursospao;

// Inscripcion.java
public class Inscripcion {
    private final Estudiante estudiante; // asociación hacia Estudiante
    private final Curso curso; // asociación hacia Curso (interfaz/abstracto)
    private double notaFinal;
    private boolean asistio;
    
// Constructor
    // Se ejecuta al crear una inscripción, recibiendo un estudiante y un curso
    // Inicializa la nota en 0 y la asistencia en falso por defecto
    public Inscripcion(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.notaFinal = 0;
        this.asistio = false;
    }
   // Metodos 
   // Devuelve el estudiante asociado  
    public Estudiante getEstudiante() { return estudiante; }
    // Devuelve el curso asociado
    public Curso getCurso() { return curso; }
    // devuelve la nota final del estudiante en el curso
    public double getNotaFinal() { return notaFinal; }

    // asigna o actualiza la nota final del estudiante en el curso
    public void setNotaFinal(double nota) { this.notaFinal = nota; }
    
    // indica si el estudiante asistió al curso
    public boolean isAsistio() { return asistio; }

    // marca la asistencia del estudiante
    public void marcarAsistencia(boolean v) { this.asistio = v; }
}