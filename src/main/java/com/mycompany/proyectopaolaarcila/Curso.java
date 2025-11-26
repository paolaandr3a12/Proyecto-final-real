package com.mycompany.proyectopaolaarcila;

import java.util.ArrayList;

public abstract class Curso {
    protected String codigo;
    protected String titulo;
    protected Instructor instructor;
    protected ArrayList<Sesion> sesiones;
    protected ArrayList<Inscripcion> inscripciones;

    public Curso(String codigo, String titulo, Instructor instructor, ArrayList<Inscripcion> inscripciones) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.instructor = instructor;
        this.sesiones = new ArrayList<>();
        this.inscripciones = inscripciones;
    }

    public abstract double calcularNotaFinal(Estudiante e);

    public void agregarSesion(String fecha, String tema) {
        Sesion s = new Sesion(fecha, tema);
        sesiones.add(s);
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null) inscripciones.add(inscripcion);
    }

    public void inscribir(Estudiante e) {
        Inscripcion ins = new Inscripcion(e, this);
        agregarInscripcion(ins);
    }

    public ArrayList<Inscripcion> getInscripciones() { return inscripciones; }
    public String getTitulo() { return titulo; }
    public String getCodigo() { return codigo; }
}