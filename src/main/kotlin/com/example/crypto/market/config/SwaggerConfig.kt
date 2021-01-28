package com.example.crypto.market.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket{
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.crypto.market"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
    }

    private fun metaData(): ApiInfo{
        return ApiInfo(
                "Crypto Market",
                "tutorial",
                "1.0",
                "terens of license",
                Contact("Filip Kazmierczak", "", ""),
                "Apache license version 2.0",
                "",
                emptyList()
        )
    }
}