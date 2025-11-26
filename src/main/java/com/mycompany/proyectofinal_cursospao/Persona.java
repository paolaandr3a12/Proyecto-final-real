
package com.mycompany.proyectofinal_cursospao;
// Persona.java
public abstract class Persona {
    private String id;
    private String nombre;
    private String correo;

    public Persona(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y setters (encapsulamiento)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    // Método abstracto que obliga a las subclases a implementar cómo mostrar info
    public abstract String descripcion();
}