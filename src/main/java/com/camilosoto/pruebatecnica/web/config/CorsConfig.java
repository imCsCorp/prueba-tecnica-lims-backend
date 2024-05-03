package com.camilosoto.pruebatecnica.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource (){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(List.of("*")); // Permitir cualquier origen (con credentials, usa allowedOriginPatterns)
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Permitir métodos permitidos
        corsConfiguration.setAllowedHeaders(List.of("*")); // Permitir todos los encabezados
        corsConfiguration.setExposedHeaders(List.of("Authorization")); // Exponer encabezados personalizados
        corsConfiguration.setAllowCredentials(true); // Permitir el intercambio de credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
