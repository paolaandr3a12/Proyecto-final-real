package com.mycompany.proyectopaolaarcila;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class GeneradorPDF {

    // ------------------------------------------------------------
    // CONFIGURACIÓN GENERAL
    // ------------------------------------------------------------
    private static final String CARPETA = "Reportes";
    private static final String LOGO_RUTA = "images/brand.png";

    private static final Font FONT_TITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, new BaseColor(40, 100, 40));  // Verde oscuro pastel
    private static final Font FONT_SUBTITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, new BaseColor(40, 100, 40));
    private static final Font FONT_NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.DARK_GRAY);
    private static final Font FONT_TABLA_HEADER = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);

    // Colores Pastel Verde
    private static final BaseColor VERDE_PASTEL = new BaseColor(204, 255, 229);
    private static final BaseColor VERDE_PASTEL_SUAVE = new BaseColor(235, 255, 245);
    private static final BaseColor VERDE_ENCABEZADO = new BaseColor(70, 160, 110);

    private static void crearCarpeta() {
        File carpeta = new File(CARPETA);
        if (!carpeta.exists()) carpeta.mkdirs();
    }

    private static void agregarEspacio(Document doc, int cantidad) throws DocumentException {
        for (int i = 0; i < cantidad; i++) {
            doc.add(new Paragraph(" "));
        }
    }

    private static void agregarLogo(Document doc) {
        try {
            Image img = Image.getInstance(LOGO_RUTA);
            img.scaleToFit(160, 160);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);
            agregarEspacio(doc, 1);
        } catch (Exception ignored) {}
    }

    // Celda con color de fondo pastel
    private static PdfPCell celda(String texto, Font fuente, BaseColor fondo) {
        PdfPCell c = new PdfPCell(new Paragraph(texto, fuente));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c.setPadding(7);
        if (fondo != null) c.setBackgroundColor(fondo);
        return c;
    }

    // ============================================================
    // =============== 1. FACTURA INDIVIDUAL ======================
    // ============================================================
    public static void generarFactura(String nombreEstudiante, String correo, String curso, boolean pago) {

        crearCarpeta();
        Document doc = new Document();

        try {
            String archivo = CARPETA + File.separator + ("Factura_" + nombreEstudiante.replace(" ", "_") + ".pdf");

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(archivo));
            doc.open();

            // Fondo pastel
            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.setColorFill(VERDE_PASTEL_SUAVE);
            canvas.rectangle(0, 0, PageSize.A4.getWidth(), PageSize.A4.getHeight());
            canvas.fill();

            agregarLogo(doc);

            doc.add(new Paragraph("FACTURA - TARJETA SPARK", FONT_TITULO));
            agregarEspacio(doc, 1);

            doc.add(new Paragraph("Estudiante: " + nombreEstudiante, FONT_NORMAL));
            doc.add(new Paragraph("Correo: " + correo, FONT_NORMAL));
            doc.add(new Paragraph("Curso: " + curso, FONT_NORMAL));
            agregarEspacio(doc, 1);

            doc.add(new Paragraph(
                    pago ? "Tarjeta Spark: ACTIVA (Acceso Premium)" 
                         : "Tarjeta Spark: NO ACTIVA (Acceso limitado)",
                    FONT_NORMAL
            ));

            doc.add(new Paragraph("Valor: " + (pago ? "$50.000 COP" : "$0 COP"), FONT_NORMAL));
            agregarEspacio(doc, 2);

            Paragraph gracias = new Paragraph("Gracias por confiar en Learnix ", FONT_SUBTITULO);
            gracias.setAlignment(Element.ALIGN_CENTER);
            doc.add(gracias);

        } catch (Exception e) {
            System.out.println("Error generando factura: " + e.getMessage());
        } finally {
            doc.close();
        }
    }

    // ============================================================
    // =============== 2. CONSOLIDADO DE ESTUDIANTES ===============
    // ============================================================
    public static void generarConsolidadoEstudiantes(ArrayList<String[]> estudiantes) {

        crearCarpeta();
        Document doc = new Document();

        try {
            String archivo = CARPETA + File.separator + "Consolidado_Estudiantes.pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(archivo));
            doc.open();

            agregarLogo(doc);

            doc.add(new Paragraph("CONSOLIDADO DE ESTUDIANTES", FONT_TITULO));
            agregarEspacio(doc, 1);

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);

            // Encabezados verdes
            tabla.addCell(celda("ID", FONT_TABLA_HEADER, VERDE_ENCABEZADO));
            tabla.addCell(celda("Nombre", FONT_TABLA_HEADER, VERDE_ENCABEZADO));
            tabla.addCell(celda("Correo", FONT_TABLA_HEADER, VERDE_ENCABEZADO));

            // Filas en verde pastel
            for (String[] e : estudiantes) {
                tabla.addCell(celda(e[0], FONT_NORMAL, VERDE_PASTEL_SUAVE));
                tabla.addCell(celda(e[1], FONT_NORMAL, VERDE_PASTEL));
                tabla.addCell(celda(e[2], FONT_NORMAL, VERDE_PASTEL_SUAVE));
            }

            doc.add(tabla);

        } catch (Exception e) {
            System.out.println("Error generando consolidado estudiantes: " + e.getMessage());
        } finally {
            doc.close();
        }
    }

    // ============================================================
    // =============== 3. CONSOLIDADO DE PAGOS =====================
    // ============================================================
    public static void generarConsolidadoPagos(ArrayList<String[]> estudiantes, ArrayList<Boolean> pagos) {

        crearCarpeta();
        Document doc = new Document();

        try {
            String archivo = CARPETA + File.separator + "Consolidado_Pagos.pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(archivo));
            doc.open();

            agregarLogo(doc);

            doc.add(new Paragraph("CONSOLIDADO DE PAGOS - TARJETA SPARK", FONT_TITULO));
            agregarEspacio(doc, 1);

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);

            tabla.addCell(celda("Nombre", FONT_TABLA_HEADER, VERDE_ENCABEZADO));
            tabla.addCell(celda("Correo", FONT_TABLA_HEADER, VERDE_ENCABEZADO));
            tabla.addCell(celda("Estado", FONT_TABLA_HEADER, VERDE_ENCABEZADO));

            int totalPagados = 0;

            for (int i = 0; i < estudiantes.size(); i++) {

                boolean pago = (i < pagos.size() && pagos.get(i) != null) && pagos.get(i);

                tabla.addCell(celda(estudiantes.get(i)[1], FONT_NORMAL, VERDE_PASTEL_SUAVE));
                tabla.addCell(celda(estudiantes.get(i)[2], FONT_NORMAL, VERDE_PASTEL));
                tabla.addCell(celda(pago ? "Pagado" : "No pagado", FONT_NORMAL, VERDE_PASTEL_SUAVE));

                if (pago) totalPagados++;
            }

            doc.add(tabla);
            agregarEspacio(doc, 1);

            doc.add(new Paragraph("Total pagados: " + totalPagados, FONT_NORMAL));
            doc.add(new Paragraph("Total sin pago: " + (estudiantes.size() - totalPagados), FONT_NORMAL));

        } catch (Exception e) {
            System.out.println("⚠ Error generando consolidado pagos: " + e.getMessage());
        } finally {
            doc.close();
        }
    }

    // ============================================================
    // =============== 4. CONSOLIDADO CURSO + SPARK ===============
    // ============================================================
    public static void generarConsolidadoPagosConSpark(ArrayList<String[]> estudiantes, ArrayList<Boolean> pagosCurso, ArrayList<Boolean> pagosSpark) {

        crearCarpeta();
        Document doc = new Document();

        try {
            String archivo = CARPETA + File.separator + "Consolidado_Pagos_y_Spark.pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(archivo));
            doc.open();

            agregarLogo(doc);

            doc.add(new Paragraph("CONSOLIDADO DE PAGOS - CURSO & TARJETA SPARK", FONT_TITULO));
            agregarEspacio(doc, 1);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);

            tabla.addCell(celda("Nombre", FONT_TABLA_HEADER, VERDE_ENCABEZADO));
            tabla.addCell(celda("Correo", FONT_TABLA_HEADER, VERDE_ENCABEZADO));
            tabla.addCell(celda("Pago Curso", FONT_TABLA_HEADER, VERDE_ENCABEZADO));
            tabla.addCell(celda("Tarjeta Spark", FONT_TABLA_HEADER, VERDE_ENCABEZADO));

            int totalCurso = 0;
            int totalSpark = 0;

            for (int i = 0; i < estudiantes.size(); i++) {

                boolean pagoCurso = (i < pagosCurso.size() && pagosCurso.get(i) != null) && pagosCurso.get(i);
                boolean pagoSpark = (i < pagosSpark.size() && pagosSpark.get(i) != null) && pagosSpark.get(i);

                tabla.addCell(celda(estudiantes.get(i)[1], FONT_NORMAL, VERDE_PASTEL_SUAVE));
                tabla.addCell(celda(estudiantes.get(i)[2], FONT_NORMAL, VERDE_PASTEL));
                tabla.addCell(celda(pagoCurso ? "✔ Pagado" : "✘ No pagado", FONT_NORMAL, VERDE_PASTEL_SUAVE));
                tabla.addCell(celda(pagoSpark ? "Activa" : "No activa", FONT_NORMAL, VERDE_PASTEL));

                if (pagoCurso) totalCurso++;
                if (pagoSpark) totalSpark++;
            }

            doc.add(tabla);
            agregarEspacio(doc, 1);

            doc.add(new Paragraph("Total que pagaron el curso: " + totalCurso, FONT_NORMAL));
            doc.add(new Paragraph("Tarjetas Spark activas: " + totalSpark, FONT_NORMAL));

        } catch (Exception e) {
            System.out.println("⚠ Error generando consolidado Spark: " + e.getMessage());
        } finally {
            doc.close();
        }
    }

    // ============================================================
    // =============== 5. RESUMEN DE ASISTENCIA ===================
    // ============================================================
    public static void generarResumenAsistencia(int total, int asistieron, int faltaron) {

        crearCarpeta();
        Document doc = new Document();

        try {

            String archivo = CARPETA + File.separator + "Resumen_Asistencia.pdf";
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(archivo));
            doc.open();

            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.setColorFill(VERDE_PASTEL_SUAVE);
            canvas.rectangle(0, 0, PageSize.A4.getWidth(), PageSize.A4.getHeight());
            canvas.fill();

            agregarLogo(doc);

            doc.add(new Paragraph("RESUMEN DE ASISTENCIA", FONT_TITULO));
            agregarEspacio(doc, 1);

            doc.add(new Paragraph("Total inscritos: " + total, FONT_NORMAL));
            doc.add(new Paragraph("Asistieron: " + asistieron, FONT_NORMAL));
            doc.add(new Paragraph("Faltaron: " + faltaron, FONT_NORMAL));

        } catch (Exception e) {
            System.out.println(" Error generando resumen asistencia: " + e.getMessage());
        } finally {
            doc.close();
        }
    }
}
