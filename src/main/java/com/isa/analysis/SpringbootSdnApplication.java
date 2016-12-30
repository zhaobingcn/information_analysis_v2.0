package com.isa.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * @author zhaobing
 */
@SpringBootApplication
public class SpringbootSdnApplication {

    public static void main(String[] args){
        SpringApplication.run(SpringbootSdnApplication.class, args);
    }
}
