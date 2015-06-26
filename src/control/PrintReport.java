/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


 
import java.io.FileOutputStream;
import java.io.IOException;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
 
/**
 * First iText example: Hello World.
 */
public class PrintReport {
 
    
    
    
    /** Path to the resulting PDF file. */
    public static  String RESULT;
 
    /**
     * Creates a PDF file: hello.pdf
     * @param    args    no arguments needed
     */
    public PrintReport(String name,JTable table)
    	 {
        
      
        try {
            createPdf(name,table);
             JOptionPane.showMessageDialog(null, "Documento Creado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo exportar");
        } 
        
    }
 
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename, JTable table)
	throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Documento de Reporte Artículos Bajo el Minimo" + "                   " + (new Date().toLocaleString())));
        document.add(new Paragraph("Hogarcito / XXXXXX"));
      

        document.add(new Paragraph("               "));
        document.add(new Paragraph("               "));
        
       
        int f = 0;
        int c = table.getRowCount();
        
        document.add(new Paragraph("< --------------- Inicio Reporte --------------- >"));
        document.add(new Paragraph("                   "));
        document.add(new Paragraph("                   "));
        document.add(new Paragraph("         ID Articulo |     Nombre       |    Cantidad      |     Cantidad Minima"));
        try{
        while (f != c) {

            String idArt = table.getValueAt(f, 0).toString();
            String nombre = table.getValueAt(f, 1).toString();
            String Cantidad = table.getValueAt(f, 2).toString();
            String minima = table.getValueAt(f, 3).toString();

            System.out.println(idArt);
            if(idArt!= null){
            document.add(new Paragraph(f+ "-          "+ idArt + "        |         " + nombre + "\t           "
                    + "       |      " + Cantidad + "          |       " + minima));
            
            
            
            }
            f= f + 1;
        } 
       
        
        
        
        }
        catch(Exception e){
        document.add(new Paragraph("                   "));
        document.add(new Paragraph("                   "));
        document.add(new Paragraph("< ----------------- Fin Reporte ----------------- >"));
        }
        
         
        // step 5
        document.close();
    }
    
    public void addTable(Document document) throws DocumentException{
            
        PdfPTable table = new PdfPTable(3); // 3 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            document.add(table);
    }
}