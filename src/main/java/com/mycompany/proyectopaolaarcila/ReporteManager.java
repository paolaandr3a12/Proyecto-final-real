package com.mycompany.proyectopaolaarcila;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReporteManager {

    private static final String FOLDER = "Reportes";

    // Crea la carpeta donde se guardarán los PDFs si no existe
    private static void asegurarCarpeta() {
        File dir = new File(FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
    }

 
}