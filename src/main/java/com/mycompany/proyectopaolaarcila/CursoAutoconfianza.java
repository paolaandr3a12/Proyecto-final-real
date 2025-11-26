package com.mycompany.proyectopaolaarcila;

import java.util.ArrayList;

public class CursoAutoconfianza extends Curso {

    public CursoAutoconfianza(String codigo, String titulo, Instructor instructor) {
        super(codigo, titulo, instructor, new ArrayList<>());
    }

    @Override
    public double calcularNotaFinal(Estudiante e) {
        double base = e.promedio();
        long asistencias = getInscripciones().stream()
                .filter(i -> i.getEstudiante().getId().equals(e.getId()) && i.isAsistio())
                .count();

        long totalSesiones = sesiones.isEmpty() ? 1 : sesiones.size();
        double proporcion = (double) asistencias / totalSesiones;
        return Math.max(0.0, base * 0.6 + proporcion * 2.0);
    }
}