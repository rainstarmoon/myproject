package com.myproject.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
    
	/**
     * 防止netty的bug
     * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
     */
    @PostConstruct
    private void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    
}
