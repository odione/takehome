package br.com.ebanx.takehome.setup;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder
        .serializationInclusion(Include.NON_NULL)
        .indentOutput(true)
        .postConfigurer(this::configurePrinter);
  }

  void configurePrinter(ObjectMapper objectMapper) {
    var printer = new MinimalPrettyPrinter() {
      @Override
      public void writeStartObject(JsonGenerator g) throws IOException {
        g.writeRaw(" {");
      }

      @Override
      public void writeObjectEntrySeparator(JsonGenerator g) throws IOException {
        g.writeRaw(", ");
      }
    };
    objectMapper.setDefaultPrettyPrinter(printer);
  }
}
