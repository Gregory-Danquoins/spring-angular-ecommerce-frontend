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

import com.fufu.ecommerce.entity.Country;
import com.fufu.ecommerce.entity.Product;
import com.fufu.ecommerce.entity.ProductCategory;
import com.fufu.ecommerce.entity.State;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{

    private EntityManager entityManager;
    private RepositoryRestConfiguration config;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager){
        this.entityManager = entityManager;
        
    }
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){

        HttpMethod[] unsupportedActions = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        Class[] protectedClass = {Product.class,ProductCategory.class,Country.class,State.class};

        for (Class theClass : protectedClass)
        {
            disableHttpMethods(config,unsupportedActions,theClass);
        }

        // disableHttpMethods(config,theUnsupportedActions,Product.class);
        // disableHttpMethods(config,theUnsupportedActions,ProductCategory.class);
        // disableHttpMethods(config,theUnsupportedActions,Country.class);
        // disableHttpMethods(config,theUnsupportedActions,State.class);


        // call an internal helper method to expose the id
        exposeIds(config);
    }

    private void disableHttpMethods(RepositoryRestConfiguration config,HttpMethod[] methods, Class theClass){
        config.getExposureConfiguration()
        .forDomainType(theClass)
        .withItemExposure((metdata, HttpMethods)-> HttpMethods.disable(methods))
        .withCollectionExposure((metdata, HttpMethods)-> HttpMethods.disable(methods));
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