package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.FacturaCabecera;
import com.cultodeportivo.Modelos.FacturaDetalle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class FacturaPDF {

    public ArrayList<FacturaDetalle> detalles;
    public FacturaCabecera cabecera;

    public FacturaPDF(FacturaCabecera cabecera) {
        this.cabecera = cabecera;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(FacturaDetalle newFac) {
        this.detalles.add(newFac);
    }

    public void generarPDF() {
        Document document = new Document(PageSize.LETTER, 0, 0, 0, 0);
        String numero = cabecera.getCabId() + "";

        String direccion = cabecera.getCliente().getPersona().getPerDireccion();
        String nombre = cabecera.getCliente().nombreCompleto();
        String fecha = cabecera.getCabFecha() + "";
        String numeroEmpleado = cabecera.getUsuario().getUsrNombre();

        this.cabecera.actualizarData(detalles);
        String nombreEmpresa = "Clinica Dental S.A.";

        String direccionEmpresa = "Calle ficticia lomas verdes para la quinta lucrecio de las mercedes quinde cocho";

        if (direccionEmpresa.length() > 33) {
            direccionEmpresa = direccionEmpresa.substring(0, 33) + "...";
        }

        String ruc = "0000011";

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Ejemplo_pdf_java.pdf"));
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            Graphics g2d = cb.createGraphicsShapes(PageSize.LETTER.getWidth(), PageSize.LETTER.getHeight());

            Color fondoClaro = new Color(191, 252, 245);
            Color limites = new Color(162, 219, 213);
            Color negro = new Color(0, 0, 0);

            // Fondo
            g2d.setColor(fondoClaro);
            g2d.fillRect(0, 0, 612, 792);

            // Cuadro de Clinica
            g2d.setColor(limites);
            g2d.drawRect(15, 15, 582, 150);

            ImageIcon diente = new ImageIcon(getClass().getResource("diente.png"));
            g2d.drawImage(diente.getImage(), 30, 30, 120, 120, null);

            Font titulo = new Font("Tahoma", Font.BOLD, 50);
            g2d.setFont(titulo);
            g2d.setColor(negro);
            g2d.drawString("Clinica Dental", 195, 110);

            // Cuadro de Cliente
            g2d.setColor(limites);
            g2d.drawRect(15, 180, 582, 100);

            Font contenido = new Font("Tahoma", Font.BOLD, 12);
            g2d.setFont(contenido);
            g2d.setColor(negro);
            g2d.drawString("Numero de Factura:", 23, 197);
            g2d.drawString("Nombre:", 23, 222);
            g2d.drawString("Direccion:", 23, 247);
            g2d.drawString("Fecha:", 23, 272);

            Font contenidoReducido = new Font("Tahoma", Font.PLAIN, 12);
            g2d.setFont(contenidoReducido);
            g2d.drawString(numero, 150, 197);
            g2d.drawString(nombre, 80, 222);
            g2d.drawString(direccion, 90, 247);
            g2d.drawString(fecha.substring(0, 10), 70, 272);

            g2d.setFont(contenido);
            g2d.drawString("Nombre de Usuario:", 300, 197);
            g2d.drawString("Empresa:", 300, 222);
            g2d.drawString("RUC:", 300, 247);

            g2d.setFont(contenidoReducido);
            g2d.drawString(numeroEmpleado, 435, 197);
            g2d.drawString(nombreEmpresa, 365, 222);
            g2d.drawString(ruc, 335, 247);

            // Cuadro de servicios
            g2d.setColor(limites);
            g2d.drawRect(15, 295, 582, 300);
            g2d.drawRect(15, 310, 582, 285);

            int columnWidth = 582 / 6;
            int servicioWidth = columnWidth * 2; // Hacer la columna de "Servicio" el doble de grande
            int remainingWidth = (582 - servicioWidth) / 5; // Ajustar el ancho de las demás columnas

            g2d.drawRect(15 + servicioWidth, 295, remainingWidth, 300);
            g2d.drawRect(15 + servicioWidth + remainingWidth, 295, remainingWidth, 300);
            g2d.drawRect(15 + servicioWidth + remainingWidth * 2, 295, remainingWidth, 300);
            g2d.drawRect(15 + servicioWidth + remainingWidth * 3, 295, remainingWidth, 300);
            g2d.drawRect(15 + servicioWidth + remainingWidth * 4, 295, remainingWidth, 300);

            // Textos de encabezado
            int inicial = 308;
            g2d.setColor(negro);
            g2d.drawString("Servicio", 20, inicial);
            g2d.drawString("Precio", 15 + servicioWidth + 5, inicial);
            g2d.drawString("Cantidad", 15 + servicioWidth + remainingWidth + 5, inicial);
            g2d.drawString("Subtotal", 15 + servicioWidth + remainingWidth * 2 + 5, inicial);
            g2d.drawString("IVA", 15 + servicioWidth + remainingWidth * 3 + 5, inicial);
            g2d.drawString("Total", 15 + servicioWidth + remainingWidth * 4 + 5, inicial);

            // Agregar detalles
            for (int i = 0; i < detalles.size(); i++) {
                inicial += 20;
                FacturaDetalle detalle = detalles.get(i);
                g2d.drawString(detalle.getServicio().getSerNombre(), 20, inicial);
                g2d.drawString(String.valueOf(detalle.getServicio().getSerPrecio()), 15 + servicioWidth + 5, inicial);
                g2d.drawString(String.valueOf(detalle.getDetCantidad()), 15 + servicioWidth + remainingWidth + 5, inicial);
                g2d.drawString(String.valueOf(detalle.getDetSubtotal()), 15 + servicioWidth + remainingWidth * 2 + 5, inicial);
                g2d.drawString(String.valueOf(detalle.getDetIva()), 15 + servicioWidth + remainingWidth * 3 + 5, inicial);
                g2d.drawString(String.valueOf(detalle.getDetTotal()), 15 + servicioWidth + remainingWidth * 4 + 5, inicial);
            }

            // Subtotales, IVA y Total
            g2d.drawString("Subtotal", 15 + servicioWidth + remainingWidth * 3 + 5, 550);
            g2d.drawString("IVA", 15 + servicioWidth + remainingWidth * 3 + 5, 570);
            g2d.drawString("Total", 15 + servicioWidth + remainingWidth * 3 + 5, 590);

            g2d.drawString(cabecera.getCabSubtotal() + "", 15 + servicioWidth + remainingWidth * 4 + 5, 550);
            g2d.drawString(cabecera.getCabTotalIva() + "", 15 + servicioWidth + remainingWidth * 4 + 5, 570);
            g2d.drawString(cabecera.getCabTotal() + "", 15 + servicioWidth + remainingWidth * 4 + 5, 590);

            g2d.setColor(limites);
            g2d.drawRect(15, 610, 582, 100);

            String info = "Factura creada de forma automática mediante el proceso de facturación electrónica.";
            String info2 = "Esta factura ha sido no solo enviada a la base de datos creada por los estudiantes Jorge Cueva, ";
            String info3 = "Felipe Peralta, Cinthya Ramon, y Johnny Segarra, sino que se ha enviado de forma automática al cliente ";
            String info4 = "al que le pertenece el correo eletrónico designado durante el registro de la persona y el cliente en la ";
            String info5 = "base de datos";

            g2d.setColor(negro);
            g2d.drawString(info, 90, 630);
            g2d.drawString(info2, 55, 645);
            g2d.drawString(info3, 27, 660);
            g2d.drawString(info4, 40, 675);
            g2d.drawString(info5, 260, 690);

            // Cuadro de derechos
            g2d.setColor(limites);
            g2d.drawRect(15, 725, 582, 52);

            g2d.dispose();
            document.close();
            System.out.println("Se creó el archivo 'Ejemplo_pdf_java.pdf' en la carpeta del proyecto");

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
