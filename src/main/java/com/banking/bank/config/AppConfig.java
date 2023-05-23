package com.banking.bank.config;

import com.banking.bank.filter.JwtTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilterFilterRegistrationBean(){
        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtTokenFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
