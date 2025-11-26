
package com.mycompany.proyectofinal_cursospao;

import java.util.ArrayList;

// Estudiante Hereda de persona y mantiene una lista de notas
public class Estudiante extends Persona {
    private final ArrayList<Double> notas; // colección ArrayList

// Constructor que inicia los campos heredados
    public Estudiante(String id, String nombre, String correo) {
        super(id, nombre, correo);
        this.notas = new ArrayList<>();
    }

    public void agregarNota(double nota) {
        notas.add(nota);
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }
 
// Metodo que describe al estrudiante
    @Override
    public String descripcion() {
        return "Estudiante: " + getNombre() + " (ID: " + getId() + ")";
    }
    
// Calcula el promedio aritmetico de las notas y si no hay nota devuelve 0
    public double promedio() {
        if (notas.isEmpty()) return 0;
        double suma = 0;
        for (double n : notas) suma += n;
        return suma / notas.size();
    }
}
