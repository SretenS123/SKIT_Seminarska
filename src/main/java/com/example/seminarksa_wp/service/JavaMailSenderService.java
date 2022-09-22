package com.example.seminarksa_wp.service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

public interface JavaMailSenderService {
    void mailSend(String subject, String htmlMsg, String sendTo);
}
