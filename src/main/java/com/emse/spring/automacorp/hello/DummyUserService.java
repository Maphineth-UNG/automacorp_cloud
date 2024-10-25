package com.emse.spring.automacorp.hello;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DummyUserService implements UserService{
    @Override
    public void greetAll(List<String> names) {
        for (String name : names) {
            System.out.println("Hello, " + name + "!");
        }
    }
}
