package cn.bug.admin.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;


/**
 * Description
 *
 * @author by bug
 * @Date 2021/9/22
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Value("${jwt.config.header}")
    private String header;

    @Bean
    public Docket createRestApi() {
        //swagger设置，基本信息，要解析的接口及路径等
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
                //设置通过什么方式定位需要自动生成文档的接口，这里定位方法上的@ApiOperation注解
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // .apis(RequestHandlerSelectors.basePackage("cn.bug.qmswebapi.controller"))
                //接口URI路径设置，any是全路径，也可以通过PathSelectors.regex()正则匹配
                .paths(PathSelectors.any()).build()
                .securityContexts(List.of(securityContext()))
                // ApiKey的name需与SecurityReference的reference保持一致
                .securitySchemes(List.of(new ApiKey(header, header, "header")));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("/*.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference(header, authorizationScopes));
    }


    //生成接口信息，包括标题、联系人，联系方式等
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("如有疑问，请联系开发工程师")
                .contact(new Contact("coding-bug", "", "swl4988@qq.com"))
                .version("1.0")
                .build();
    }
}
