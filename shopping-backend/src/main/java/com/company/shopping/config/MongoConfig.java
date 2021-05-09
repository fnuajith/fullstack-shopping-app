package com.company.shopping.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Mongo configuration
 * Reference: https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo-template.instantiating
 * 
 * @author Ajith
 *
 */
@Configuration
public class MongoConfig {
	
	@Value("${mongodb.uri}")
	private String uri;
	
	@Value("${mongodb.databaseName}")
	private String databaseName;
	
	@Value("${mongodb.userName}")
	private String userName;
	
	@Value("${mongodb.password}")
	private String password;

    @Bean
    public MongoClient mongoClient() throws UnsupportedEncodingException {
    	 return MongoClients.create(getConnectionString());
    }
    
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), this.databaseName);
        return mongoTemplate;
    }
    
    private String getConnectionString() throws UnsupportedEncodingException {
    	String uri = this.uri;
    	String[] uriParts = uri.split("://");
    	StringBuilder connectionStringBuilder = new StringBuilder();
    	connectionStringBuilder.append(uriParts[0]);
    	connectionStringBuilder.append("://");
    	connectionStringBuilder.append(this.userName);
    	connectionStringBuilder.append(":");
    	connectionStringBuilder.append(URLEncoder.encode(this.password, "UTF-8"));
    	connectionStringBuilder.append("@");
    	connectionStringBuilder.append(uriParts[1]);
    	return connectionStringBuilder.toString();
    }

}