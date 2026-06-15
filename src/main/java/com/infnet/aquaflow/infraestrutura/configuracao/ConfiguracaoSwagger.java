package com.infnet.aquaflow.infraestrutura.configuracao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoSwagger {

    @Bean
    public OpenAPI aquaflowOpenApi () {
        return new OpenAPI().info(new Info().title("AquaFlow API").description(
                        "API REST para gestao de academia de natacao com arquitetura hexagonal. " + "As respostas de erro seguem o contrato ErroPadraoResposta.")
                .version("v1").contact(new Contact().name("Equipe AquaFlow"))
                .license(new License().name("Uso academico")));
    }
}
