package com.company.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration to enable swagger
 * 
 * References: 
 * http://springfox.github.io/springfox/docs/current/#introduction
 * http://springfox.github.io/springfox/docs/current/#property-file-lookup
 *  
 * @author Ajith
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.company.shopping")).paths(PathSelectors.any()).build()
				.pathMapping("/").apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Shopping Service APIs").description("Explore the shopping service APIs")
				.version("1.0").build();
	}
}
