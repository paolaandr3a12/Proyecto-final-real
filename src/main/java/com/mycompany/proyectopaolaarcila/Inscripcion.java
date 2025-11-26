package com.mycompany.proyectopaolaarcila;

public class Inscripcion {
    private final Estudiante estudiante; //asociacion
    private final Curso curso; //asociacion
    private double notaFinal;
    private boolean asistio;

    public Inscripcion(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.notaFinal = 0;
        this.asistio = false;
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Curso getCurso() { return curso; }
    public double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(double notaFinal) { this.notaFinal = notaFinal; }
    public boolean isAsistio() { return asistio; }
    public void marcarAsistencia(boolean asistio) { this.asistio = asistio; }
}