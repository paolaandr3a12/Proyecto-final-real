
package com.mycompany.proyectofinal_cursospao;

// clase instructor hereda de la clase persona
public class Instructor extends Persona {

    // Atributo 
    private String especialidad;

    public Instructor(String id, String nombre, String correo, String especialidad) {
        super(id, nombre, correo);
        this.especialidad = especialidad;
    }
// Metodo para obtener y establecer la especialidad
    public String getEspecialidad() {  return especialidad; }
    
    // Metodo para establecer la especialidad
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    @Override
    public String descripcion() {
        return "Instructor: " + getNombre() + " - " + especialidad;
    }
}