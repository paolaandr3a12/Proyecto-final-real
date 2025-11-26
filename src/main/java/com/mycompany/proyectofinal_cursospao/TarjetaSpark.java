/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal_cursospao;


import java.util.ArrayList;

public class TarjetaSpark implements AccesoRecursos {
    
    // Atributos
    private final boolean activa; // true si el estudiante pagó

    private final String tipoCurso;

    public TarjetaSpark(String tipoCurso, boolean activa) {
       
       // Inicializa los atributos de la tarjeta
        this.tipoCurso = tipoCurso;
        this.activa = activa;
    }

    @Override
    public ArrayList<String> obtenerRecursosBasicos() {
        ArrayList<String> recursos = new ArrayList<>();
        recursos.add("Guía introductoria del curso " + tipoCurso);
        recursos.add("Podcast educativo básico");
        recursos.add("Artículo de lectura libre");
        return recursos;
    }

    @Override
    public ArrayList<String> obtenerRecursosPremium() {
        ArrayList<String> recursos = new ArrayList<>();
        recursos.add("Video avanzado del curso " + tipoCurso);
        recursos.add("Libro digital completo");
        recursos.add("Acceso a biblioteca virtual y simuladores");
        return recursos;
    }

    @Override
    public void mostrarRecursos(boolean accesoPremium) {
        try {
            System.out.println("\n Recursos disponibles para el curso " + tipoCurso + ":");
            ArrayList<String> recursos = accesoPremium ? obtenerRecursosPremium() : obtenerRecursosBasicos();
            for (String r : recursos) {
                System.out.println(" - " + r);
            }
            if (!accesoPremium) {
                throw new IllegalAccessException("⚠️ Acceso limitado: actualiza a TarjetaSpark Premium para ver más recursos.");
            }
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isActiva() {
        return activa;
    }
}