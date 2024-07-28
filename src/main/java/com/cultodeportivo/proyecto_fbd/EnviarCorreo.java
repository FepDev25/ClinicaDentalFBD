package com.cultodeportivo.proyecto_fbd;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarCorreo {

    public void enviarCorreo(String destinatario) {
        String archivoAdjunto = "Ejemplo_pdf_java.pdf";
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        propiedad.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        final String correoEnviar = "clinicadentalcprs@gmail.com";
        final String appPassword = "rszk lkxu sgjk jdlo"; // Use la contraseña de la aplicación aquí

        Session ses = Session.getInstance(propiedad, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEnviar, appPassword);
            }
        });

        // Activar el modo de depuración para obtener más información sobre el problema
        ses.setDebug(true);

        try {
            MimeMessage mail = new MimeMessage(ses);
            mail.setFrom(new InternetAddress(correoEnviar));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject("Factura de Compra");

            // Crear el contenido HTML
            String contenidoHTML = "<html>"
                + "<body>"
                + "<h1>Factura de compra</h1>"
                + "</body>"
                + "</html>";

            MimeBodyPart cuerpoMensaje = new MimeBodyPart();
            cuerpoMensaje.setContent(contenidoHTML, "text/html");

            // Crear el contenedor de partes del mensaje
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpoMensaje);

            // Adjuntar el archivo PDF
            MimeBodyPart adjuntoPDF = new MimeBodyPart();
            adjuntoPDF.attachFile(archivoAdjunto);
            multipart.addBodyPart(adjuntoPDF);

            // Adjuntar el contenido al correo
            mail.setContent(multipart);

            Transport.send(mail);

            System.out.println("Correo enviado exitosamente");

        } catch (IOException | MessagingException | NoClassDefFoundError e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}

