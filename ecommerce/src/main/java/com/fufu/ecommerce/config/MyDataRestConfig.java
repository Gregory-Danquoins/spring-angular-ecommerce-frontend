package com.fufu.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.HttpMethods;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.fufu.ecommerce.entity.Product;
import com.fufu.ecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){

        HttpMethod[] theUnsupportedActions = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // disable http methods for Product: PUT, POST, DELETE

        config.getExposureConfiguration()
        .forDomainType(Product.class)
        .withItemExposure((metdata, HttpMethods)-> HttpMethods.disable(theUnsupportedActions))
        .withCollectionExposure((metdata, HttpMethods)-> HttpMethods.disable(theUnsupportedActions));

        // disable http methods for ProductCategory: PUT, POST, DELETE

        config.getExposureConfiguration()
        .forDomainType(ProductCategory.class)
        .withItemExposure((metdata, HttpMethods)-> HttpMethods.disable(theUnsupportedActions))
        .withCollectionExposure((metdata, HttpMethods)-> HttpMethods.disable(theUnsupportedActions));
    

    }
}