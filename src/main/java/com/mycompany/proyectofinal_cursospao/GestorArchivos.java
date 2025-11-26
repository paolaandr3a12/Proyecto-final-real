package com.mycompany.proyectofinal_cursospao;

import java.io.*;
import java.util.ArrayList;

public class GestorArchivos {

    // Guarda las notas finales en un archivo de texto
    public static void guardarNotas(ArrayList<Estudiante> estudiantes, Curso curso) {
        File archivo = new File("notas_" + curso.getCodigo() + ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write("=== Registro de notas del curso: " + curso.getTitulo() + " ===\n");
            for (Estudiante e : estudiantes) {
                double notaFinal = curso.calcularNotaFinal(e);
                bw.write(e.getNombre() + " (" + e.getId() + ") → Nota Final: " + notaFinal + "\n");
            }
            System.out.println(" Archivo de notas guardado exitosamente en: " + archivo.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println(" Error al guardar el archivo: " + ex.getMessage());
        }
    }

    // Lee y muestra el contenido del archivo de notas
    public static void leerNotas(String codigoCurso) {
        File archivo = new File("notas_" + codigoCurso + ".txt");
        if (!archivo.exists()) {
            System.out.println("⚠️ No existe un archivo de notas para el curso " + codigoCurso);
            return;
        }

        System.out.println("\n=== Lectura del archivo de notas ===");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException ex) {
            System.out.println("⚠️ Error al leer el archivo: " + ex.getMessage());
        }
    }
}

