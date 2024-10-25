package com.emse.spring.automacorp;

import com.emse.spring.automacorp.hello.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration  // (1) Marks the class as a configuration class for Spring
public class AutomacorpApplicationConfig {

    @Bean  // (2) Declares that this method returns a Spring-managed bean
    public CommandLineRunner greetingCommandLine(GreetingService greetingService) {  // (3) Injects GreetingService
        return args -> {
            greetingService.greet("Hello, Spring ");  // (4) Calls the greet method to output "Hello, Spring!" to the console at startup
        };
    }
}
