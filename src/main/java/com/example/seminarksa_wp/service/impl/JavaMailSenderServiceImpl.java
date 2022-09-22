package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.service.JavaMailSenderService;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class JavaMailSenderServiceImpl implements JavaMailSenderService {
    @Autowired
    private  JavaMailSender javaMailSender;
    @Override
    public void mailSend(String subject, String htmlMsg, String sendTo) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            message.setContent(htmlMsg, "text/html");
            helper.setTo(sendTo);
            helper.setSubject(subject);
            javaMailSender.send(message);
        } catch (MessagingException e) {

        }
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(email);
//        msg.setSubject(subject);
//
//        msg.setText(text);
//        javaMailSender.send(msg);
    }
}
