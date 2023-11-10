package com.panel.wg.restapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@OpenAPIDefinition(
        info = @Info(
                title = "Portal APIs",
                version = "1.0"
        ),
//        servers = {
//                @Server(
//                        description = "dev",
//                        url = "localhost:8080"
//                )
//        },
        security = {
//                @SecurityRequirement(name = "bearerAuth")
        }

)

@SecuritySchemes(
        {
                @SecurityScheme(
                        name = "bearerAuth",
                        description = "JWT Auth authentication",
                        scheme = "bearer",
                        type = SecuritySchemeType.HTTP,
                        bearerFormat = "JWT",
                        in = SecuritySchemeIn.HEADER
                ),
                @SecurityScheme(
                        name = "creditor-token",
                        description = "debtor provide a creditor token",
                        type = SecuritySchemeType.APIKEY,
                        in = SecuritySchemeIn.HEADER
                )
        }

)
public class OpenApiConfig {
}
