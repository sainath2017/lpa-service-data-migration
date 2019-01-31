/*-
* This software is the property of:
*
* World Fuel Services Corporation.
* Copyright (c) 2017 World Fuel Services Corporation.
*
* It may not be copied, distributed or modified, in part or in whole,
* by any means whatsoever, without the explicit written permission of World Fuel Services Corporation.
*/
package com.wfs.landpricing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String GROUP_NAME = "lpa";

  private static final String OVERVIEW = "LPA REST Services";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).groupName(GROUP_NAME)
        .apiInfo(apiInfo())
        .select()
        // .apis(withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build()
        .forCodeGeneration(true);
  }

  private ApiInfo apiInfo() {
    String confluencePage = "http://wiki-mia.wfs.com/pages/viewpage.action?pageId=10067087";
    Contact xFalconsContact = new Contact("lpa", confluencePage, "lpa@wfscorp.com");

    return new ApiInfoBuilder().title(OVERVIEW)
        .contact(xFalconsContact)
        .build();
  }

}
