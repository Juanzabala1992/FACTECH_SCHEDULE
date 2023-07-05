package com.login.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("factech4dm1n@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            String htmlContent = "<div class=\"container\">\n" +
                    "        <div class=\"title\" style=\"display: flex; justify-content: center;\">\n" +
                    "            <h2>\n" +
                    "                Bienvenido al servicio web de registro de horas\n" +
                    "            </h2>\n" +
                    "        </div>\n" +
                    "        \n" +
                    "        <br>\n" +
                    "        <div class=\"content\" style=\"display: flex; justify-content: center;\">\n" +
                    "            <p>Esta es tu contraseña, recuerda que no puedes compartirla, \n" +
                    "                recuerda una vez inicia sesión en tu perfil podrás cambiarla!:  </p> \n"+
                    "        </div>          \n" + "<br><b>"+body+"</b"+
                    "         <br>\n" +
                    "        <br>\n" +
                    "        <div style=\"display: flex; justify-content: center;\">\n" +
                    "             <div style='width:200px'>\n" +
                    "                <img style='width:200px' src=\"https://i5wffc.p3cdn1.secureserver.net/wp-content/uploads/2021/09/cropped-Logo-sin-fondo-1536x414.png\" alt=\"\"> \n" +
                    "            </div>\n" +
                    "            <div style=\"text-align: center; align-self: center;\">\n" +
                    "                <p align=\"left\">                    \n" +
                    "                    <b>FacTECH</b> Servicios Informáticos <br>\n" +
                    "                    Servicios de TI y consultoría de TI &#128187;\n" +
                    "                </p>      \n" +
                    "            </div>            \n" +
                    "        </div>\n" +
                    "        <div style=\"display: flex; justify-content: center;\">\n" +
                    "            <br>\n" +
                    "            <p><p style='font-size:12px;'>No respondas este correo, si requieres soporte comunicate con el administrador</p></p>\n" +
                    "        </div>\n" +
                    "    </div>";
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            // Manejo de excepciones
        }
    }

}
