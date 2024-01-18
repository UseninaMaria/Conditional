package com.example.springbootapp.config;

import com.example.springbootapp.models.DevProfile;
import com.example.springbootapp.models.ProductionProfile;
import com.example.springbootapp.models.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.example")
public class SpringConfig {

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false" )
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
