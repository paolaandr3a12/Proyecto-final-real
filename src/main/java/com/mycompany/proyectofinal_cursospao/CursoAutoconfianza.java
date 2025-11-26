
package com.mycompany.proyectofinal_cursospao;

// CursoAutoconfianza.java
public class CursoAutoconfianza extends Curso {
    public CursoAutoconfianza(String codigo, String titulo, Instructor instructor) {
        super(codigo, titulo, instructor);
    }

    @Override
    public double calcularNotaFinal(Estudiante e) {
        // distinto algoritmo: promediamos, pero penalizamos faltas
        double base = e.promedio();
        long asistencias = getInscripciones().stream()
                .filter(i -> i.getEstudiante().getId().equals(e.getId()) && i.isAsistio())
                .count();
        long totalSesiones = sesiones.isEmpty() ? 1 : sesiones.size();
        double proporcion = (double) asistencias / totalSesiones;
        return Math.max(0.0, base * 0.6 + proporcion * 2.0); // ejemplo personalizado
    }
}