package com.mycompany.proyectopaolaarcila;

public class Sesion {
    //Atributos 
    private final String fecha;
    private final String tema;

    
    //constructor
    public Sesion(String fecha, String tema) {
        this.fecha = fecha;
        this.tema = tema;
    }
    // metodos 
    public String getFecha() { return fecha; }
    public String getTema() { return tema; }
}