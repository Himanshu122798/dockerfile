package com.carpool.general.service.impl.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import com.carpool.SpringBootApp;

/**
 * This auto configuration will be used by spring boot to enable traditional deployment to a servlet container. You may
 * remove this class if you run your application with embedded tomcat only. Tomcat startup will be twice as fast.
 */
@Configuration
@EnableAutoConfiguration
public class ServletInitializer extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

    return application.sources(SpringBootApp.class);
  }
}
