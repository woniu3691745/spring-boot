package com.lidl.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lidl.swagger.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("李栋梁", "http://localhost:8080", "email地址");
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2")
                .description("Hello Swagger2")
//                .termsOfServiceUrl("http://www.jianshu.com/u/f192766abeab")
//                .contact("作者名")
                .contact(contact)
                .version("1.0")
                .license("http://localhost:8080")
                .build();
    }

}
