package com.mycompany.proyectopaolaarcila;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    // =====================================================
    //   SECCIÓN 0 —>  PANTALLA DE INICIO
    // =====================================================

    private JFrame pantallaInicio;

    public void mostrarPantallaInicio() {

        pantallaInicio = new JFrame("Learnix Academy - Bienvenida");
        pantallaInicio.setSize(700, 500);
        pantallaInicio.setLocationRelativeTo(null);
        pantallaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaInicio.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel arriba = new JPanel();
        arriba.setBackground(Color.WHITE);

        try {
            ImageIcon icon = new ImageIcon(
                "C:/Documentos/NetBeansProjects/com.mycompany_ProyectoPaolaArcila_jar_1.0-SNAPSHOT/images/brand.png"
            );

            Image esc = icon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            arriba.add(new JLabel(new ImageIcon(esc)));

        } catch (Exception e) {
            JLabel def = new JLabel("LEARNIX");
            def.setFont(new Font("Segoe UI", Font.BOLD, 40));
            arriba.add(def);
        }

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("BIENVENIDO AL SISTEMA DE CURSOS VIRTUALES ");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JLabel sub = new JLabel("Learnix Academy");
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        sub.setForeground(new Color(90, 90, 90));

        JButton ingresar = new JButton("Ingresar al Sistema");
        ingresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingresar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        ingresar.setBackground(new Color(100, 160, 100));  
        ingresar.setForeground(Color.WHITE);
        ingresar.setFocusPainted(false);
        ingresar.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        ingresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ingresar.addActionListener(e -> {
            pantallaInicio.dispose();
            this.setVisible(true);
        });

        centro.add(Box.createVerticalStrut(20));
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(sub);
        centro.add(Box.createVerticalStrut(40));
        centro.add(ingresar);

        JLabel footer = new JLabel("© 2025 Learnix Academy", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(Color.GRAY);

        panel.add(arriba, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);

        pantallaInicio.add(panel);
        pantallaInicio.setVisible(true);
    }

    // =====================================================
    //   SECCIÓN 1 —> TODA TU VENTANA PRINCIPAL ORIGINAL
    // =====================================================

    private JTextField txtIdInstructor, txtNombreInstructor, txtCorreoInstructor, txtEspecialidad;
    private JTextField txtCodigoCurso, txtTituloCurso;
    private JComboBox<String> cbTipoCurso;

    private JTextField txtFechaSesion, txtTemaSesion;
    private JButton btnAgregarSesion;

    private JTextField txtIdEst, txtNombreEst, txtCorreoEst;
    private JButton btnInscribirEst;

    private JButton btnRegistrarPago;

    private JButton btnAgregarNotas, btnMarcarAsistencias, btnGenerarReportes;
    private JButton btnRegistrarAsistencia;

    private JTextField txtTotalEstudiantes, txtAsistieron;
    private JTextArea areaSalida;

    private Curso cursoActual;
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<String[]> listaEstudiantes = new ArrayList<>();
    private ArrayList<Boolean> listaPagos = new ArrayList<>();
    private ArrayList<Boolean> listaSpark = new ArrayList<>();

    private final Color verdeFondo = new Color(200, 230, 200);
    private final Color verdeOscuro = new Color(100, 160, 100);
    private final Color grisClaro = new Color(240, 240, 240);

    private JButton btnPantallaFinal;  // <-- añadido

    public VentanaPrincipal() {

        this.setVisible(false);
        mostrarPantallaInicio();

        setTitle("LEARNIX - SISTEMA DE GESTION DE CURSOS VIRTUALES");
        setSize(900, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(verdeFondo);

        JPanel panelInstructor = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInstructor.setBorder(BorderFactory.createTitledBorder("Datos del Instructor"));
        panelInstructor.setBackground(grisClaro);

        txtIdInstructor = new JTextField();
        txtNombreInstructor = new JTextField();
        txtCorreoInstructor = new JTextField();
        txtEspecialidad = new JTextField();

        panelInstructor.add(new JLabel("ID:"));
        panelInstructor.add(txtIdInstructor);
        panelInstructor.add(new JLabel("Nombre:"));
        panelInstructor.add(txtNombreInstructor);
        panelInstructor.add(new JLabel("Correo:"));
        panelInstructor.add(txtCorreoInstructor);
        panelInstructor.add(new JLabel("Especialidad:"));
        panelInstructor.add(txtEspecialidad);

        JPanel panelCurso = new JPanel(new GridLayout(4, 2, 5, 5));
        panelCurso.setBorder(BorderFactory.createTitledBorder("Datos del Curso"));
        panelCurso.setBackground(grisClaro);

        cbTipoCurso = new JComboBox<>(new String[]{"Autoconfianza", "Alimentación Saludable"});
        txtCodigoCurso = new JTextField();
        txtTituloCurso = new JTextField();
        JButton btnCrearCurso = new JButton("Crear Curso");
        estilizarBoton(btnCrearCurso);

        panelCurso.add(new JLabel("Tipo de Curso:"));
        panelCurso.add(cbTipoCurso);
        panelCurso.add(new JLabel("Código:"));
        panelCurso.add(txtCodigoCurso);
        panelCurso.add(new JLabel("Título:"));
        panelCurso.add(txtTituloCurso);
        panelCurso.add(new JLabel(""));
        panelCurso.add(btnCrearCurso);

        btnCrearCurso.addActionListener(e -> crearCurso());

        JPanel panelSesion = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSesion.setBorder(BorderFactory.createTitledBorder("Sesiones del Curso"));
        panelSesion.setBackground(grisClaro);

        txtFechaSesion = new JTextField();
        txtTemaSesion = new JTextField();
        btnAgregarSesion = new JButton("Agregar Sesión");
        estilizarBoton(btnAgregarSesion);

        panelSesion.add(new JLabel("Fecha:"));
        panelSesion.add(txtFechaSesion);
        panelSesion.add(new JLabel("Tema:"));
        panelSesion.add(txtTemaSesion);
        panelSesion.add(new JLabel(""));
        panelSesion.add(btnAgregarSesion);

        btnAgregarSesion.addActionListener(e -> agregarSesion());

        JPanel panelEstudiantes = new JPanel(new GridLayout(5, 2, 5, 5));
        panelEstudiantes.setBorder(BorderFactory.createTitledBorder("Registro de Estudiantes"));
        panelEstudiantes.setBackground(grisClaro);

        txtIdEst = new JTextField();
        txtNombreEst = new JTextField();
        txtCorreoEst = new JTextField();
        btnInscribirEst = new JButton("Inscribir Estudiante");
        estilizarBoton(btnInscribirEst);

        btnRegistrarPago = new JButton("Registrar Pago");
        estilizarBoton(btnRegistrarPago);

        panelEstudiantes.add(new JLabel("ID:"));
        panelEstudiantes.add(txtIdEst);
        panelEstudiantes.add(new JLabel("Nombre:"));
        panelEstudiantes.add(txtNombreEst);
        panelEstudiantes.add(new JLabel("Correo:"));
        panelEstudiantes.add(txtCorreoEst);
        panelEstudiantes.add(btnInscribirEst);
        panelEstudiantes.add(btnRegistrarPago);

        btnInscribirEst.addActionListener(e -> inscribirEstudiante());
        btnRegistrarPago.addActionListener(e -> registrarPago());

        JPanel panelFunciones = new JPanel(new GridLayout(5, 2, 10, 10));  // <-- ampliado 4→5 filas
        panelFunciones.setBorder(BorderFactory.createTitledBorder("Gestión del Curso"));
        panelFunciones.setBackground(grisClaro);

        btnAgregarNotas = new JButton("Agregar Notas");
        btnMarcarAsistencias = new JButton("Marcar Asistencias");
        btnGenerarReportes = new JButton("Generar Reportes PDF");
        estilizarBoton(btnAgregarNotas);
        estilizarBoton(btnMarcarAsistencias);
        estilizarBoton(btnGenerarReportes);

        txtTotalEstudiantes = new JTextField();
        txtAsistieron = new JTextField();
        btnRegistrarAsistencia = new JButton("Calcular Faltantes");
        estilizarBoton(btnRegistrarAsistencia);

        // NUEVO BOTÓN FINALIZAR SISTEMA
        btnPantallaFinal = new JButton("Finalizar Sistema");
        estilizarBoton(btnPantallaFinal);

        panelFunciones.add(btnAgregarNotas);
        panelFunciones.add(btnMarcarAsistencias);
        panelFunciones.add(btnGenerarReportes);
        panelFunciones.add(new JLabel("Total Estudiantes:"));
        panelFunciones.add(txtTotalEstudiantes);
        panelFunciones.add(new JLabel("Asistieron:"));
        panelFunciones.add(txtAsistieron);
        panelFunciones.add(btnRegistrarAsistencia);
        panelFunciones.add(btnPantallaFinal);

        areaSalida = new JTextArea(15, 40);
        areaSalida.setEditable(false);
        areaSalida.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultados"));

        JPanel panelArriba = new JPanel(new GridLayout(2, 1));
        panelArriba.setBackground(verdeFondo);
        panelArriba.add(panelInstructor);
        panelArriba.add(panelCurso);

        JPanel panelCentro = new JPanel(new GridLayout(3, 1));
        panelCentro.setBackground(verdeFondo);
        panelCentro.add(panelSesion);
        panelCentro.add(panelEstudiantes);
        panelCentro.add(panelFunciones);

        add(panelArriba, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(scroll, BorderLayout.EAST);

        btnAgregarNotas.addActionListener(e -> agregarNotas());
        btnMarcarAsistencias.addActionListener(e -> marcarAsistencias());
        btnGenerarReportes.addActionListener(e -> generarReportes());
        btnRegistrarAsistencia.addActionListener(e -> calcularFaltantes());

        // Acción del botón Finalizar Sistema
        btnPantallaFinal.addActionListener(e -> {
            this.dispose();
            mostrarPantallaFinal();
        });
    }

    // =====================================================
    //   SECCIÓN 2 → FUNCIONES + PAGO SPARK
    // =====================================================

    private void estilizarBoton(JButton boton) {
        boton.setBackground(verdeOscuro);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(70, 120, 70)));
    }

    private void crearCurso() {
        try {
            String id = txtIdInstructor.getText().trim();
            String nombre = txtNombreInstructor.getText().trim();
            String correo = txtCorreoInstructor.getText().trim();
            String especialidad = txtEspecialidad.getText().trim();

            if (id.isEmpty() || nombre.isEmpty() || correo.isEmpty() || especialidad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los datos del instructor.");
                return;
            }

            Instructor instructor = new Instructor(id, nombre, correo, especialidad);
            String tipoCurso = (String) cbTipoCurso.getSelectedItem();
            String codigo = txtCodigoCurso.getText().trim();
            String titulo = txtTituloCurso.getText().trim();

            if (tipoCurso.equals("Autoconfianza")) {
                cursoActual = new CursoAutoconfianza(codigo, titulo, instructor);
            } else {
                cursoActual = new CursoAlimentacionSaludable(codigo, titulo, instructor);
            }

            areaSalida.append("Curso creado: " + titulo + " (" + tipoCurso + ")\n");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear curso: " + ex.getMessage());
        }
    }

    private void agregarSesion() {
        if (cursoActual == null) {
            JOptionPane.showMessageDialog(this, "Cree un curso primero.");
            return;
        }
        String fecha = txtFechaSesion.getText().trim();
        String tema = txtTemaSesion.getText().trim();

        cursoActual.agregarSesion(fecha, tema);

        areaSalida.append("sesion agregada: " + tema + " (" + fecha + ")\n");
    }

    // =====================================================
    //   *** AQUI SE AGREGA TODO LO DEL PAGO ***
    // =====================================================

    private void inscribirEstudiante() {

        if (cursoActual == null) {
            JOptionPane.showMessageDialog(this, "Primero debe crear un curso.");
            return;
        }

        String id = txtIdEst.getText().trim();
        String nombre = txtNombreEst.getText().trim();
        String correo = txtCorreoEst.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los datos del estudiante.");
            return;
        }

        // 1️⃣ PEDIR VALOR A PAGAR
        String valorStr = JOptionPane.showInputDialog("Ingrese el valor que debe pagar el estudiante:");
        if (valorStr == null) return;
        double valor;

        try {
            valor = Double.parseDouble(valorStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Valor invalido.");
            return;
        }

        // 2️⃣ PREGUNTAR SI DESEA PAGAR
        int deseaPagar = JOptionPane.showConfirmDialog(
            this,
            "¿Desea pagar el valor de " + valor + "?",
            "Confirmación de Pago",
            JOptionPane.YES_NO_OPTION
        );

        boolean pagoCurso = (deseaPagar == JOptionPane.YES_OPTION);

        if (pagoCurso) {
            JOptionPane.showMessageDialog(this,
                "✔ Pago realizado correctamente.\nAcceso al curso habilitado.");
        } else {
            JOptionPane.showMessageDialog(this,
                "⚠ No realizó el pago.\nAcceso limitado.");
        }

        // 3️⃣ PREGUNTAR SI DESEA TARJETA SPARK
        int compraSpark = JOptionPane.showConfirmDialog(
            this,
            "¿Desea adquirir la Tarjeta Spark y obtener beneficios adicionales?",
            "Tarjeta Spark",
            JOptionPane.YES_NO_OPTION
        );

        boolean pagoSpark = (compraSpark == JOptionPane.YES_OPTION);

        if (pagoSpark) {
            JOptionPane.showMessageDialog(this,
                "✨ Tarjeta Spark ACTIVADA.\nAcceso Premium habilitado.");
        } else {
            JOptionPane.showMessageDialog(this,
                "Tarjeta Spark NO adquirida.\nAcceso limitado.");
        }

        // 4️⃣ REGISTRAR AL ESTUDIANTE
        Estudiante e = new Estudiante(id, nombre, correo);
        cursoActual.inscribir(e);
        estudiantes.add(e);

        listaEstudiantes.add(new String[]{id, nombre, correo});

        listaPagos.add(pagoCurso);  // pago del curso
        listaSpark.add(pagoSpark);  // pago Spark

        areaSalida.append("🧾 Estudiante inscrito: " + nombre + "\n");
    }

    private void registrarPago() {
        if (estudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay estudiantes inscritos.");
            return;
        }

        String id = JOptionPane.showInputDialog("Ingrese ID del estudiante que pagó:");
        if (id == null || id.trim().isEmpty()) return;

        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getId().equals(id)) {

                listaPagos.set(i, true);

                areaSalida.append("Pago registrado para: " + estudiantes.get(i).getNombre() + "\n");
                JOptionPane.showMessageDialog(this, "Pago verificado correctamente.");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "No existe un estudiante con ese ID.");
    }

    private void agregarNotas() {
        for (Estudiante e : estudiantes) {
            String input = JOptionPane.showInputDialog("Ingrese nota final para " + e.getNombre());
            if (input != null) {
                try {
                    double nota = Double.parseDouble(input);
                    e.agregarNota(nota);
                    areaSalida.append("Nota registrada para " + e.getNombre() + ": " + nota + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Valor inválido.");
                }
            }
        }
    }

    private void marcarAsistencias() {
        for (Estudiante e : estudiantes) {
            int asistencias = JOptionPane.showConfirmDialog(this, "¿" + e.getNombre() + " asistió a todas las sesiones?", "Asistencia", JOptionPane.YES_NO_OPTION);
            if (asistencias == JOptionPane.YES_OPTION) {
                for (Inscripcion ins : cursoActual.getInscripciones()) {
                    if (ins.getEstudiante().equals(e)) ins.marcarAsistencia(true);
                }
                areaSalida.append(" Asistencia marcada para " + e.getNombre() + "\n");
            }
        }
    }

    private void generarReportes() {

        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante e = estudiantes.get(i);

            boolean spark = listaSpark.get(i);

            GeneradorPDF.generarFactura(
                e.getNombre(),
                e.getCorreo(),
                cursoActual.getTitulo(),
                spark
            );
        }

        GeneradorPDF.generarConsolidadoEstudiantes(listaEstudiantes);
        GeneradorPDF.generarConsolidadoPagos(listaEstudiantes, listaPagos);

        areaSalida.append(" Reportes PDF generados correctamente.\n");
    }

    private void calcularFaltantes() {
        try {
            int total = Integer.parseInt(txtTotalEstudiantes.getText().trim());
            int asistieron = Integer.parseInt(txtAsistieron.getText().trim());
            if (asistieron > total) {
                JOptionPane.showMessageDialog(this, "Asistentes no puede ser mayor al total.");
                return;
            }
            int faltaron = total - asistieron;
            areaSalida.append("\n Total: " + total + " | ✅ Asistieron: " + asistieron + " | ❌ Faltaron: " + faltaron + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.");
        }
    }

    // =====================================================
    //   SECCIÓN ESPECIAL —> PANTALLA FINAL
    // =====================================================

    public void mostrarPantallaFinal() {

        JFrame pantallaFinal = new JFrame("Gracias por usar Learnix Academy");
        pantallaFinal.setSize(700, 500);
        pantallaFinal.setLocationRelativeTo(null);
        pantallaFinal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaFinal.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel arriba = new JPanel();
        arriba.setBackground(Color.WHITE);

        try {
            ImageIcon icon = new ImageIcon(
                "C:/Documentos/NetBeansProjects/com.mycompany_ProyectoPaolaArcila_jar_1.0-SNAPSHOT/images/brand.png"
            );

            Image esc = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            arriba.add(new JLabel(new ImageIcon(esc)));

        } catch (Exception e) {
            JLabel def = new JLabel("LEARNIX");
            def.setFont(new Font("Segoe UI", Font.BOLD, 40));
            arriba.add(def);
        }

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("¡MUCHAS GRACIAS POR USAR LEARNIX ACADEMY!");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub1 = new JLabel("Tu crecimiento personal es nuestro propósito.");
        sub1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub1.setAlignmentX(Component.CENTER_ALIGNMENT);
        sub1.setForeground(new Color(70, 70, 70));

        JLabel sub2 = new JLabel("Esperamos verte nuevamente muy pronto.");
        sub2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub2.setAlignmentX(Component.CENTER_ALIGNMENT);
        sub2.setForeground(new Color(70, 70, 70));

        centro.add(Box.createVerticalStrut(30));
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(15));
        centro.add(sub1);
        centro.add(Box.createVerticalStrut(10));
        centro.add(sub2);
        centro.add(Box.createVerticalStrut(40));

        JButton btnSalir = new JButton("Cerrar Sistema");
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnSalir.setBackground(new Color(100, 160, 100));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSalir.addActionListener(ev -> System.exit(0));

        centro.add(btnSalir);

        JLabel footer = new JLabel("© 2025 Learnix Academy — Sistema de Cursos Virtuales", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(Color.GRAY);

        panel.add(arriba, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);

        pantallaFinal.add(panel);
        pantallaFinal.setVisible(true);
    }

    // =====================================================
    //  SECCIÓN 3 —> MAIN
    // =====================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
