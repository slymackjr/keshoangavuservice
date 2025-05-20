package org.keshoangavu.keshoangavu.business.service;

import org.keshoangavu.keshoangavu.data.dto.ContactFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendContactEmail(ContactFormDTO form) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo("info@keshoangavu.org");
        helper.setReplyTo(form.getEmail());
        helper.setSubject("New Contact Form Submission");
        helper.setText(
                "<h2>New Contact Form Submission</h2>" +
                        "<p><strong>First Name:</strong> " + form.getFirstName() + "</p>" +
                        "<p><strong>Last Name:</strong> " + form.getLastName() + "</p>" +
                        "<p><strong>Email:</strong> " + form.getEmail() + "</p>" +
                        "<p><strong>Message:</strong> " + form.getMessage() + "</p>",
                true
        );

        mailSender.send(message);
    }
}