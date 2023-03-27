package org.ditalia.bbb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
	//registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/"); // Linux 
	registry.addResourceHandler("/images/**").addResourceLocations("file:c:/vestidos/img-vestidos/"); // Windows
	}
}
