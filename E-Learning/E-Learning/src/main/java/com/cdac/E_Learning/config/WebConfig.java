package com.cdac.E_Learning.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//        		.allowedOrigins("http://paramrudra.pune.cdac.in","http://paramrudra.pune.cdac.in:9096", "http://localhost:4302", "http://localhost:4302", "http://paramrudra.pune.cdac.in:4302","http://localhost:4302", "http://localhost:4302") 
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
        
    }
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/opt/LMS/images/");
        registry.addResourceHandler("/notes/**")
        .addResourceLocations("file:/opt/LMS/notes/");
        registry.addResourceHandler("/assignments/**")
        .addResourceLocations("file:/opt/LMS/assignments/");
        registry.addResourceHandler("/userAnswers/**")
        .addResourceLocations("file:/opt/LMS/userAnswers/");
        registry.addResourceHandler("/videos/**")
        .addResourceLocations("file:/opt/LMS/videos/");
        registry.addResourceHandler("/certificates/**")
        .addResourceLocations("file:/opt/LMS/certificates/");
    }
    
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:4200") // Adjust as necessary for your frontend URL
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//            }
//        };
//    }
    
  
}
