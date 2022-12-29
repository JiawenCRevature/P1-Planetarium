package com.revature.planetarium.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private LoggerInterceptor loggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/api/**").order(1);
        registry.addInterceptor(loggerInterceptor).addPathPatterns("/**").order(Ordered.HIGHEST_PRECEDENCE);
    }

    
}
