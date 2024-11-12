package com.example.newspringbootproject;

import com.example.newspringbootproject.interceptor.MultiInterceptor1;
import com.example.newspringbootproject.interceptor.MultiInterceptor2;
import com.example.newspringbootproject.interceptor.MultiInterceptor3;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@MapperScan("com.example.newspringbootproject.mapper")
public class NewSpringBootProjectApplication implements WebMvcConfigurer {
    public static void main(String[] args){
        SpringApplication.run(NewSpringBootProjectApplication.class);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir1 = registry.addInterceptor(new MultiInterceptor1());
        ir1.addPathPatterns("/interceptor/*");

        InterceptorRegistration ir2 = registry.addInterceptor(new MultiInterceptor2());
        ir2.addPathPatterns("/interceptor/*");

        InterceptorRegistration ir3 = registry.addInterceptor(new MultiInterceptor3());
        ir3.addPathPatterns("/interceptor/*");

        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("language");
        registry.addInterceptor(lci);
    }

    @Bean(name = "localeResolver")
    public LocaleResolver initLocalResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);

        return slr;
    }
}
