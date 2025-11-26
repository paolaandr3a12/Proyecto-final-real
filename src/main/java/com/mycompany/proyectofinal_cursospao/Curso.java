
package com.mycompany.proyectofinal_cursospao;
// Curso.java
import java.util.ArrayList;

public abstract class Curso {
    protected String codigo;
    protected String titulo;
    protected Instructor instructor;
    // composición: las sesiones pertenecen al curso y su ciclo de vida depende del curso
    protected ArrayList<Sesion> sesiones;
    // agregación/relación: inscripciones (pueden existir por fuera pero están relacionadas)
    protected ArrayList<Inscripcion> inscripciones;

    public Curso(String codigo, String titulo, Instructor instructor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.instructor = instructor;
        this.sesiones = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
    }

    // método abstracto que será polimórficamente implementado en subclases
    public abstract double calcularNotaFinal(Estudiante e);

    public void agregarSesion(String fecha, String tema) {
        Sesion s = new Sesion(fecha, tema);
        sesiones.add(s);
    }

    public void inscribir(Estudiante e) {
        Inscripcion ins = new Inscripcion(e, this);
        inscripciones.add(ins);
    }

    public ArrayList<Inscripcion> getInscripciones() { return inscripciones; }

    public String getTitulo() { return titulo; }
    public String getCodigo() { return codigo; }

    // Clase interna que representa una sesión (composición)
    public class Sesion {
        private String fecha;
        private String tema;

        public Sesion(String fecha, String tema) {
            this.fecha = fecha;
            this.tema = tema;
        }

        public String getFecha() { return fecha; }
        public String getTema() { return tema; }
    }
}