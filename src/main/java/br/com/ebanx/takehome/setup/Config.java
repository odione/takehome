package br.com.ebanx.takehome.setup;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder
        .serializationInclusion(Include.NON_NULL);
  }
}
