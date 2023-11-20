package com.akinnova.Ecoms.email;

import org.springframework.http.ResponseEntity;

public interface IEmailService {
    ResponseEntity<?> sendSimpleEmail(EmailDetail emailDetail);
    ResponseEntity<?> sendEmailWithAttachment(EmailDetail emailDetail);
}
