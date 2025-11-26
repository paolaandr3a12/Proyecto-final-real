/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal_cursospao;

import java.util.ArrayList;

public interface AccesoRecursos {
    ArrayList<String> obtenerRecursosBasicos();
    ArrayList<String> obtenerRecursosPremium();
    void mostrarRecursos(boolean accesoPremium);
}