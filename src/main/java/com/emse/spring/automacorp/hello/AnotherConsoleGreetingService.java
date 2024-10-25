package com.emse.spring.automacorp.hello;

import org.springframework.stereotype.Service;

@Service
public class AnotherConsoleGreetingService implements GreetingService {

    @Override
    public void greet(String name) {  // Implementing greet with a String parameter
        System.out.println("Bonjour, " + name + "!");  // Use the name parameter in the greeting
    }
}
