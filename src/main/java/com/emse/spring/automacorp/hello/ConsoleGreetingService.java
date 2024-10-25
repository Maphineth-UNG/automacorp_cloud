package com.emse.spring.automacorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Primary
@Component
public class ConsoleGreetingService implements GreetingService {
    @Override
    public void greet(String spring) {
        System.out.println("Hello, Spring!");
    }
}

