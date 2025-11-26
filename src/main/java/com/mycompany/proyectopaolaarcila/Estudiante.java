package com.mycompany.proyectopaolaarcila;

import java.util.ArrayList;

public class Estudiante extends Persona {
    private final ArrayList<Double> notas;

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

    @Override
    public String descripcion() {
        return "Estudiante: " + getNombre() + " (ID: " + getId() + ")";
    }

    public double promedio() {
        if (notas.isEmpty()) return 0;
        double suma = 0;
        for (double n : notas) suma += n;
        return suma / notas.size();
    }
}