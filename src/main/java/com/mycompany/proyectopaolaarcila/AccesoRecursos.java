package com.mycompany.proyectopaolaarcila;

import java.util.ArrayList;
// Interfaz que define los métodos que cualquier clase
// relacionada con acceso a recursos debe implementar.
// Aquí se establece qué recursos se muestran según el tipo de acceso.

public interface AccesoRecursos {
    ArrayList<String> obtenerRecursosBasicos();  // Lista de recursos disponibles para usuarios sin pagar Spark.
    ArrayList<String> obtenerRecursosPremium();  // Lista de recursos para usuarios premium con Spark activado.
    void mostrarRecursos(boolean accesoPremium); // Muestra los recursos dependiendo de si la tarjeta Spark está activa.
}