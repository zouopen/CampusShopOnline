package com.evilsay.ship.Config;

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

/**
 * @Author: EvilSay
 * @Date: 2019/2/14 17:50
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket CreateApiUi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.evilsay.ship.Controller.Buyer"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("创业基地前端API接口")
                .description("前端API接口说明文档")
                .termsOfServiceUrl("")
                .contact(new Contact("EvilSay","",""))
                .version("1.0")
                .build();
    }
}
