package com.fiap.cp4.LibraryFiap.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI Documentacao() {
        Server apiServer = new Server();
        apiServer.setUrl("http://localhost:8080");
        apiServer.description("Desenvolvimento do checkpoint 4");

        Contact dados = new Contact();
        dados.setName("Leonardo");
        dados.setName("Regina");
        dados.setName("Jhonn");

        Info info = new Info().title("Checkpoint 4")
                .version("1")
                .description("Desenvolvimento do checkpoint 4");
        return new OpenAPI().info(info).servers(List.of(apiServer));
    }

}
