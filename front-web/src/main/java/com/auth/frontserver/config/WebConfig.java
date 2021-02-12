package com.auth.frontserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main.html");
        registry.addViewController("/main").setViewName("main.html");
        registry.addViewController("/signup").setViewName("signup.html");
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/signup-success").setViewName("signup-sucess.html");
        registry.addViewController("/signup").setViewName("signup.html");
        registry.addViewController("/user-manage").setViewName("user-manage.html");
    }
}