package com.andreisuslov.app;

import com.andreisuslov.resource.BookResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.MediaType;

@Component
@ApplicationPath("/app")
public class AppResourceConfig extends ResourceConfig {

    public AppResourceConfig() {

        packages("in.andreisuslov.resource", "in.andreisuslov.app");
        register(BookResource.class);

        configureSwagger();
    }

    private void configureSwagger() {
        register(ApiListingResource.class);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("2.3.1");
        beanConfig.setSchemes(new String[] { "http", "https" });
        beanConfig.setBasePath("/app");
        beanConfig.setTitle("AUT for API test automation using Rest Assured library");
        beanConfig.setDescription("https://github.com/andreisuslov/Employee-App-AUT");
        beanConfig.getSwagger().addConsumes(MediaType.APPLICATION_JSON);
        beanConfig.getSwagger().addProduces(MediaType.APPLICATION_JSON);
        beanConfig.setContact("Andrei Suslov");
        beanConfig.setResourcePackage("com.andreisuslov.resource");
        beanConfig.setPrettyPrint(false);
        beanConfig.setScan();
    }

}
