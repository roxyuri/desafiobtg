package br.com.btg.desafio.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Desafio", version = "1.0", description = "API de consumo de pedidos e geração de relatórios"))
public class OpenApiConfiguration {
}
