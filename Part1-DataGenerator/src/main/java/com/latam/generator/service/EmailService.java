package com.latam.generator.service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Envía el archivo CSV generado por correo electrónico.
 * La configuración SMTP se toma de variables de entorno, ya que son
 * credenciales y no deben quedar escritas en el código fuente.
 */
public class EmailService {

    private final String host;
    private final String port;
    private final String username;
    private final String password;

    public EmailService() {
        this.host = System.getenv("SMTP_HOST");
        this.port = System.getenv().getOrDefault("SMTP_PORT", "587");
        this.username = System.getenv("SMTP_USER");
        this.password = System.getenv("SMTP_PASSWORD");
    }

    /**
     * Indica si hay credenciales SMTP configuradas.
     */
    public boolean isConfigured() {
        return host != null && username != null && password != null;
    }

    /**
     * Envía el archivo indicado como adjunto al destinatario recibido.
     */
    public void sendCsvFile(File csvFile, String recipient) {

        if (!isConfigured()) {
            throw new IllegalStateException(
                    "SMTP no está configurado. Defina las variables de entorno " +
                            "SMTP_HOST, SMTP_USER y SMTP_PASSWORD."
            );
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("LATAM - Datos de prueba generados");

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Se adjunta el archivo CSV con los datos de prueba generados.");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(csvFile);

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException | IOException e) {
            throw new RuntimeException("Error enviando el correo.", e);
        }

    }

}
