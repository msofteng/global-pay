package org.globalti.globalpay.config;

import org.springframework.context.annotation.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfig {
  @Bean
  public OpenAPI custom() {
    return new OpenAPI(){{
      setInfo(
        new Info(){{
          setTitle("global-pay");
          setDescription("sistema de agendamento de transferências bancárias");
          setVersion("1.0.0");
        }}
      );
    }};
  }
}