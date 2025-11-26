package com.mycompany.proyectopaolaarcila;

public abstract class Persona {
    private String id;
    private String nombre;
    private String correo;

   //Constructor que inicializa los atributos cuando se crea un objeto hijo de Persona
    public Persona(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }
   // Metodos 
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }

    public abstract String descripcion();
}