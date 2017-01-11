package uk.co.comment.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "uk.co.comment.document")
public class Application {
    
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
    
    @Configuration
    @Profile("local")
    @PropertySource(value = {"classpath:application.properties", "classpath:application.local.properties"})
    public static class ConfigurationLocal {
    }
    
    @Configuration
    @Profile("test")
    @PropertySource(value = {"classpath:application.properties", "classpath:application.test.properties"})
    public static class ConfigurationTest {
    }
    
    @Configuration
    @EnableMongoRepositories
    public static class ConfigurationCommon {
        
        @Autowired
        private Environment environment;
        
        @Bean
        public MongoTemplate mongoTemplate() {
            return new MongoTemplate(new MongoClient("localhost"), environment.getProperty("database.schema"));
        }
        
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper()
                    .registerModule(new JodaModule());
        }
        
    }
    
}
