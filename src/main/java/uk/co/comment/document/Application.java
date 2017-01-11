package uk.co.comment.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

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
    public static class ConfigurationCommon {
        
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper()
                    .registerModule(new JodaModule());
        }
        
    }
    
}
