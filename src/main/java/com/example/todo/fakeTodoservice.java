package com.example.todo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class fakeTodoservice implements TodoService {
    public String doSomething(){
        return "something";
    }
}

/**
 * in this what is happening we are do dependency injection
 * basically means we are creating the interface nd implementing it in the main class and
 * passing the interface class name in the file where the spring boot bean is created
 * and from there we are concreate class is called and there object is created.
 */
