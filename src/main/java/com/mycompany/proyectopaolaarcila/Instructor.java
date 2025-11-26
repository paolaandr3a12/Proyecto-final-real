package com.mycompany.proyectopaolaarcila;

public class Instructor extends Persona {
    
    // Atributo propio de Instructor: indica su especialidad
    private String especialidad;

    
    public Instructor(String id, String nombre, String correo, String especialidad) {
        super(id, nombre, correo);
        this.especialidad = especialidad;
        // Llama al constructor de la clase Persona para asignar:
        // id, nombre y correo
        
    }
    // Método que devuelve la especialidad del instructor
    public String getEspecialidad() { return especialidad; }
    
    // Método que sobrescribe (override) el método abstracto descripcion() de Persona
    @Override
    public String descripcion() {
         // Devuelve una cadena que describe al instructor y su especialidad
        return "Instructor: " + getNombre() + " - Especialidad: " + especialidad;
    }
}