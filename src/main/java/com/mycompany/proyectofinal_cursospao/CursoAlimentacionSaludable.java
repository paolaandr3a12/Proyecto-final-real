/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal_cursospao;
// CursoAlimentacionSaludable.java
public class CursoAlimentacionSaludable extends Curso {
    public CursoAlimentacionSaludable(String codigo, String titulo, Instructor instructor) {
        super(codigo, titulo, instructor);
    }

    // Sobrescribimos el cálculo de nota final (polimorfismo)
    @Override
    public double calcularNotaFinal(Estudiante e) {
        // ejemplo: promedio simple de las notas del estudiante pero con un pequeño bono
        double base = e.promedio();
        // bono por participación en sesiones (simple ejemplo:
        long asistencias = getInscripciones().stream()
                .filter(i -> i.getEstudiante().getId().equals(e.getId()) && i.isAsistio())
                .count();
        double bono = Math.min(1.0, asistencias * 0.2);
        return Math.min(5.0, base + bono); // escala 0-5
    }
}
