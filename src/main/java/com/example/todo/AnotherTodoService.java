package com.example.todo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("anotherTodoService")
@Primary

public class AnotherTodoService implements TodoService {
    @Override
    public String doSomething() {
        return "return something from the another todoservice";
    }
}

/**
 * when we have to say spring we spring boot to handle the everything we can write @autowired
 * in this spring boot handle everything
 * but it causes something ambiguity
 * before using @autowired we were using the constructor based injection as shown in the controller
 * file
 * therefore we have to set the @Primary
 *
 *in service annotation where we going to handle bussiness logic of the problem
 */