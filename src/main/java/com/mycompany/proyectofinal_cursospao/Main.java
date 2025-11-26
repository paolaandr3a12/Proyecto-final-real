package com.mycompany.proyectofinal_cursospao;
import java.util.ArrayList;
import java.util.Scanner;

//Main paola
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== SISTEMA DE CURSOS PAARC  ===");
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
            sc.nextLine(); // limpiar buffer
            
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
            
            System.out.println("\n=== CREAR SESIONES ===");
            System.out.print("¿Cuántas sesiones desea agregar? ");
            int numSesiones = sc.nextInt();
            sc.nextLine();
            
            for (int i = 0; i < numSesiones; i++) {
                System.out.println("Sesión #" + (i + 1));
                System.out.print("Fecha: ");
                String fecha = sc.nextLine();
                System.out.print("Tema: ");
                String tema = sc.nextLine();
                curso.agregarSesion(fecha, tema);
            }
            
            System.out.println("\n=== INSCRIPCIÓN DE ESTUDIANTES ===");
            System.out.print("¿Cuántos estudiantes desea inscribir? ");
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
            }
            
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
            
            System.out.println("\n=== MARCAR ASISTENCIAS ===");
            for (Estudiante e : estudiantes) {
                System.out.println("Asistencias para " + e.getNombre());
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
            
            System.out.println("\n=== RESULTADOS ===");
            for (Estudiante e : estudiantes) {
                double notaFinal = curso.calcularNotaFinal(e);
                System.out.println(e.descripcion() + " → Nota Final: " + notaFinal);
            }
            
            System.out.println("\nCurso: " + curso.getTitulo() + " (" + curso.getCodigo() + ")");
            System.out.println("Instructor: " + instructor.getNombre());
            System.out.println("Sesiones creadas: " + numSesiones);
            System.out.println("Estudiantes inscritos: " + estudiantes.size());
            System.out.println("\n Prueba completada correctamente.");
            
            // ...
            System.out.println("\n✅ Prueba completada correctamente.");

            // === Persistencia: guardar y leer archivo ===
            GestorArchivos.guardarNotas(estudiantes, curso);
            GestorArchivos.leerNotas(curso.getCodigo());

            System.out.println("\n=== ACCESO A RECURSOS DIGITALES ===");
            for (Estudiante e : estudiantes) {
                System.out.println("\n¿El estudiante " + e.getNombre() + " pagó por la TarjetaSpark? (true/false): ");
                boolean pago = sc.nextBoolean();
                TarjetaSpark tarjeta = new TarjetaSpark(curso.getTitulo(), pago);
                tarjeta.mostrarRecursos(tarjeta.isActiva());
            }
        }
      
    }
    
    
}
