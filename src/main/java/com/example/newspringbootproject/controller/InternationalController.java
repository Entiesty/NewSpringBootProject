package com.example.newspringbootproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/international")
public class InternationalController {
    private final MessageSource messageSource;
    public InternationalController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/page")
    public String page(HttpServletRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage("msg", null, locale);
        System.out.println("msg = " + msg);

        return "international/internationalization";
    }

}
