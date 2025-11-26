package com.mycompany.proyectopaolaarcila;

import java.util.ArrayList;

public class TarjetaSpark implements AccesoRecursos {
    private final boolean activa;
    private final String tipoCurso;

  // Constructor 
    public TarjetaSpark(String tipoCurso, boolean activa) {
        this.tipoCurso = tipoCurso;
        this.activa = activa;
    }

    @Override
    public ArrayList<String> obtenerRecursosBasicos() {
        ArrayList<String> recursos = new ArrayList<>();
        recursos.add("Guía básica del curso " + tipoCurso);
        recursos.add("Podcast educativo introductorio");
        recursos.add("Lecturas gratuitas");
        return recursos;
    }

    @Override
    public ArrayList<String> obtenerRecursosPremium() {
        ArrayList<String> recursos = new ArrayList<>();
        recursos.add("Videos avanzados del curso " + tipoCurso);
        recursos.add("Libro digital completo");
        recursos.add("Simuladores y biblioteca premium");
        return recursos;
    }

    @Override
    public void mostrarRecursos(boolean accesoPremium) {
        try {
            System.out.println("\nRecursos disponibles para " + tipoCurso + ":");
            ArrayList<String> recursos = accesoPremium ? obtenerRecursosPremium() : obtenerRecursosBasicos();
            for (String r : recursos) System.out.println(" - " + r);
            if (!accesoPremium) throw new IllegalAccessException("⚠ Acceso limitado: adquiera Premium para más recursos.");
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isActiva() { return activa; }
}