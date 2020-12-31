package com.dededesignworkshop.seisankun_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConf {

    @Bean
    public Docket swaggerPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/v1.*"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Seisankun-api")
                .description("旅行の割り勘計算アプリ\"清算くん\"のAPI")
                .version("1.0.0")
                .contact(new Contact("nooboolean","https://www.facebook.com/yoshihiro.nakazawa.94","nooboolean@gmail.com"))
                .build();
    }

    @Bean
    public UiConfiguration swaggerUiConf() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .build();
    }
}
