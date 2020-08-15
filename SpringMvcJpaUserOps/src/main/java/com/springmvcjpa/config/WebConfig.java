package com.springmvcjpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.springdatajpa")
public class WebConfig implements WebMvcConfigurer {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);

		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("WebMvcConfigurer - addResourceHandlers() function get loaded...");

		// <mvc:resources mapping="/styles/**" location="/css/" />
		registry.addResourceHandler("/styles/**").addResourceLocations("/css/") // webapp/css/
				.setCachePeriod(3600).resourceChain(true) // Spring 4.1
				.addResolver(new GzipResourceResolver()) // Spring 4.1
				.addResolver(new PathResourceResolver()); // Spring 4.1

		// <mvc:resources mapping="/static/**" location="/static/" />
		registry.addResourceHandler("/static/**").addResourceLocations("/static/", "classpath:/static/") // src/main/resources/static/
				.setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
	}
}