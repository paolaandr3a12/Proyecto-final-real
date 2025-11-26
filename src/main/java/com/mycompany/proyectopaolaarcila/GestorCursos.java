package com.mycompany.proyectopaolaarcila;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorCursos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== SISTEMA DE CURSOS LEARNIX ===");

        ArrayList<String[]> listaEstudiantes = new ArrayList<>();
        ArrayList<Boolean> listaPagos = new ArrayList<>();

        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Crear y gestionar un curso completo");
            System.out.println("2. Registrar cantidad de estudiantes y asistencia general");
            System.out.println("3. Abrir interfaz grafica (Ventana Learnix)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 4) {
                System.out.println("\n Saliendo del sistema... ¡Hasta pronto!");
                break;
            }

            // ===============================================================
            // OPCIÓN 1 → CREAR Y GESTIONAR CURSO COMPLETO
            // ===============================================================
            if (opcion == 1) {
                System.out.print("Ingrese nombre del instructor: ");
                String nombreInstructor = sc.nextLine();
                System.out.print("Ingrese ID del instructor: ");
                String idInstructor = sc.nextLine();
                System.out.print("Ingrese correo del instructor: ");
                String correoInstructor = sc.nextLine();
                System.out.print("Ingrese especialidad del instructor: ");
                String especialidad = sc.nextLine();

                Instructor instructor = new Instructor(idInstructor, nombreInstructor, correoInstructor, especialidad);

                System.out.println("\nSeleccione curso a crear:");
                System.out.println("1. Curso de Autoconfianza");
                System.out.println("2. Curso de Alimentación Saludable");
                System.out.print("Opción: ");
                int opcionCurso = sc.nextInt();
                sc.nextLine();

                System.out.print("Ingrese código del curso: ");
                String codigoCurso = sc.nextLine();
                System.out.print("Ingrese título del curso: ");
                String tituloCurso = sc.nextLine();

                Curso curso;
                if (opcionCurso == 1) {
                    curso = new CursoAutoconfianza(codigoCurso, tituloCurso, instructor);
                } else {
                    curso = new CursoAlimentacionSaludable(codigoCurso, tituloCurso, instructor);
                }

                System.out.print("\n¿Cuántas sesiones desea agregar al curso? ");
                int numSesiones = sc.nextInt();
                sc.nextLine();

                for (int i = 0; i < numSesiones; i++) {
                    System.out.println("\n--- Sesión #" + (i + 1) + " ---");
                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();
                    System.out.print("Tema: ");
                    String tema = sc.nextLine();
                    curso.agregarSesion(fecha, tema);
                }

                System.out.print("\n¿Cuántos estudiantes desea inscribir? ");
                int numEst = sc.nextInt();
                sc.nextLine();

                ArrayList<Estudiante> estudiantes = new ArrayList<>();

                for (int i = 0; i < numEst; i++) {
                    System.out.println("\n--- Estudiante #" + (i + 1) + " ---");
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();

                    Estudiante e = new Estudiante(id, nombre, correo);
                    curso.inscribir(e);
                    estudiantes.add(e);

                    listaEstudiantes.add(new String[]{id, nombre, correo});
                }

                // ==========================================
                // SECCIÓN DE NOTAS
                // ==========================================
                System.out.println("\n=== AGREGAR NOTAS ===");
                for (Estudiante e : estudiantes) {
                    System.out.println("Notas para " + e.getNombre());
                    System.out.print("¿Cuántas notas desea agregar? ");
                    int nNotas = sc.nextInt();
                    for (int j = 0; j < nNotas; j++) {
                        System.out.print("Nota " + (j + 1) + ": ");
                        double nota = sc.nextDouble();
                        e.agregarNota(nota);
                    }
                }

                // ==========================================
                // SECCIÓN DE ASISTENCIAS POR SESIÓN
                // ==========================================
                System.out.println("\n=== REGISTRO DE ASISTENCIAS POR SESIÓN ===");
                for (Estudiante e : estudiantes) {
                    System.out.println("\nAsistencias para " + e.getNombre() + ":");
                    for (Inscripcion ins : curso.getInscripciones()) {
                        if (ins.getEstudiante().getId().equals(e.getId())) {
                            for (int s = 0; s < curso.sesiones.size(); s++) {
                                System.out.print("¿Asistió a la sesión " + (s + 1) + "? (true/false): ");
                                boolean asistio = sc.nextBoolean();
                                if (asistio) ins.marcarAsistencia(true);
                            }
                        }
                    }
                }

                // ==========================================
                // RESULTADOS
                // ==========================================
                System.out.println("\n=== RESULTADOS ===");
                for (Estudiante e : estudiantes) {
                    double notaFinal = curso.calcularNotaFinal(e);
                    System.out.println(e.descripcion() + " → Nota Final: " + notaFinal);
                }

                // ==========================================
                // TARJETA SPARK Y FACTURA
                // ==========================================
                System.out.println("\n=== ACCESO A RECURSOS Y FACTURA ===");
                for (Estudiante e : estudiantes) {
                    System.out.println("\n¿El estudiante " + e.getNombre() + " pagó por la TarjetaSpark? (true/false): ");
                    boolean pago = sc.nextBoolean();
                    TarjetaSpark tarjeta = new TarjetaSpark(curso.getTitulo(), pago);
                    tarjeta.mostrarRecursos(tarjeta.isActiva());
                    listaPagos.add(pago);

                    // Generar factura individual PDF
                    GeneradorPDF.generarFactura(e.getNombre(), e.getCorreo(), curso.getTitulo(), pago);
                }

                // ==========================================
                // REPORTES GENERALES PDF
                // ==========================================
                GeneradorPDF.generarConsolidadoEstudiantes(listaEstudiantes);
                GeneradorPDF.generarConsolidadoPagos(listaEstudiantes, listaPagos);

                System.out.println("\n✅ Reportes PDF generados correctamente en la carpeta 'Reportes'.");
                System.out.println("==============================================\n");
            }

            // ===============================================================
            // OPCIÓN 2 → REGISTRAR CANTIDAD Y ASISTENCIA GENERAL
            // ===============================================================
            else if (opcion == 2) {
                System.out.println("\n=== REGISTRO DE ASISTENCIA GENERAL ===");

                System.out.print("Ingrese la cantidad total de estudiantes: ");
                int total = sc.nextInt();

                System.out.print("Ingrese la cantidad de estudiantes que asistieron: ");
                int asistieron = sc.nextInt();

                if (asistieron > total) {
                    System.out.println("⚠ Error: no pueden asistir más estudiantes de los registrados.");
                } else {
                    int faltaron = total - asistieron;
                    System.out.println(" Total de estudiantes: " + total);
                    System.out.println(" Asistieron: " + asistieron);
                    System.out.println(" Faltaron: " + faltaron);

                    // Generar pequeño reporte PDF
                    GeneradorPDF.generarResumenAsistencia(total, asistieron, faltaron);
                    System.out.println(" Reporte de asistencia generado en carpeta 'Reportes'.");
                }
            }

            // ===============================================================
            // OPCIÓN 3 → ABRIR INTERFAZ GRÁFICA
            // ===============================================================
            else if (opcion == 3) {
                System.out.println("\n🪟 Abriendo interfaz gráfica...");
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            }

            else {
                System.out.println("⚠ Opción no válida. Intente nuevamente.");
            }
        }

        sc.close();
    }
}
