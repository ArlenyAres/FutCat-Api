package arlenyAres.FutCat_Api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(String toEmail, String name, String consultation, String subject, String details) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(toEmail);
            message.setSubject(subject);
            String emailContent = "Gracias por comunicarte con nosotros " + name + ".\n";
            emailContent += "En un momento uno de nuestros administradores se pondrá en contacto ... \n\n";
            emailContent += "Tipo de consulta: " + consultation + "\n";
            emailContent += "Detalle de la consulta: " + details + "\n\n";
            emailContent += "Atentamente. El equipo de FutCat";

            message.setText(emailContent);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e);
        }
    }
}