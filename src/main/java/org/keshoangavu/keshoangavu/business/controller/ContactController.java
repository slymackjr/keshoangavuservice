package org.keshoangavu.keshoangavu.business.controller;

import org.keshoangavu.keshoangavu.business.service.EmailService;
import org.keshoangavu.keshoangavu.data.dto.ContactFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> submitContactForm(@Valid @RequestBody ContactFormDTO form) {
        try {
            emailService.sendContactEmail(form);
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send message: " + e.getMessage());
        }
    }
}
