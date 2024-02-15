package com.fufu.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.HttpMethods;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.fufu.ecommerce.entity.Product;
import com.fufu.ecommerce.entity.ProductCategory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
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


        // call an internal helper method to expose the id
        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config){
        //expose entity ids

        // get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        
        List<Class> entityClasses = new ArrayList<>();

        // get the entity types for the entities
        for(EntityType entityType: entities){
            entityClasses.add(entityType.getJavaType());
        }

        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }
}