package com.example.portfoliospring1.service;

import com.example.portfoliospring1.controller.response.BaseException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static com.example.portfoliospring1.controller.response.BaseResponseStatusEnum.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MailUtil {

    private final JavaMailSender javaMailSender;
    String email = "";

    public String sendCodeEmail(String nickname, String email, String code) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new BaseException(INVALID_EMAIL);
        }

        MimeMessage emailForm = null;
        try {
            emailForm = createCodeEmailForm(nickname, email, code);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            javaMailSender.send(emailForm);
        } catch (MailException e) {
            e.printStackTrace();
        }

        return "success";
    }

    private MimeMessage createCodeEmailForm(String nickname, String email, String code) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("인증번호입니다");
        message.setFrom(email);
        message.setText(setCodeContext(nickname, code), "utf-8", "html");

        return message;
    }

    private String setCodeContext(String nickname, String code) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("nickname", nickname);
        context.setVariable("code", code);

        classLoaderTemplateResolver.setPrefix("templates/");
        classLoaderTemplateResolver.setSuffix(".html");
        classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
        classLoaderTemplateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(classLoaderTemplateResolver);

        return templateEngine.process("user", context);
    }

    public String sendInquiryEmail(String nickname, String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new BaseException(INVALID_EMAIL);
        }

        MimeMessage emailForm = null;
        try {
            emailForm = createInquiryEmailForm(nickname, email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            javaMailSender.send(emailForm);
        } catch (MailException e) {
            e.printStackTrace();
        }

        return "success";
    }

    private MimeMessage createInquiryEmailForm(String nickname, String email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("문의가 접수되었습니다");
        message.setFrom(email);
        message.setText(setInquiryContext(nickname), "utf-8", "html");

        return message;
    }

    private String setInquiryContext(String nickname) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("nickname", nickname);

        classLoaderTemplateResolver.setPrefix("templates/");
        classLoaderTemplateResolver.setSuffix(".html");
        classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
        classLoaderTemplateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(classLoaderTemplateResolver);

        return templateEngine.process("inquiry", context);
    }

    public String sendWelcomeEmail(String nickname, String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new BaseException(INVALID_EMAIL);
        }

        MimeMessage emailForm = null;
        try {
            emailForm = createWelcomeEmailForm(nickname, email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            javaMailSender.send(emailForm);
        } catch (MailException e) {
            e.printStackTrace();
        }

        return "success";
    }

    private MimeMessage createWelcomeEmailForm(String nickname, String email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("회원가입을 환영합니다");
        message.setFrom(email);
        message.setText(setWelcomeContext(nickname), "utf-8", "html");

        return message;
    }

    private String setWelcomeContext(String nickname) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("nickname", nickname);

        classLoaderTemplateResolver.setPrefix("templates/");
        classLoaderTemplateResolver.setSuffix(".html");
        classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
        classLoaderTemplateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(classLoaderTemplateResolver);

        return templateEngine.process("welcome", context);
    }
}
