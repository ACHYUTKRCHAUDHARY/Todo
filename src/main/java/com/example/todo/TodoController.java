package com.example.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.ArrayList;

@RestController
/**
 * Marks this class as a Spring REST controller.
 * <p>
 * A controller acts as the entry point of the Spring framework,
 * responsible for receiving HTTP requests and sending HTTP responses.
 * </p>
 *
 * <p>
 * Controllers have two main responsibilities:
 * <ul>
 *   <li>Collecting and processing incoming requests</li>
 *   <li>Returning appropriate responses</li>
 * </ul>
 * </p>
 *
 * <p>
 * When {@code @RestController} is used, Spring automatically writes
 * the returned data into the HTTP response body.
 * </p>
 */

@RequestMapping("/api/v1/todos")
public class TodoController {
//    this is the composition
    private TodoService fakeTodoservice;

    public static List<Todo> todoList ;

    public TodoController(TodoService todoservice) {
        /**
         * this is springboot way of creating the object
         */
        this.todoservice = todoservice;
        todoList=new ArrayList<>();
        todoList.add(new Todo(1,true,"MY",231));
        todoList.add(new Todo(2,false,"Ms",232));
        /**
         * Demonstrates manual object creation using plain Java.
         * In this approach, the dependency is instantiated directly
         * rather than being injected through a constructor.
         * In Spring-based applications, dependencies should be injected
         * via constructor injection. The dependent class must be annotated
         * with {@code @Component} so that Spring can manage the bean lifecycle
         * and handle dependency injection automatically.
         * Example of manual instantiation:
         * this.todoService = new TodoService();
         */
    }
    @GetMapping
    public List<Todo>  getTodoList() {
        return todoList;
    }

//    query params
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted) {
        System.out.println("Incoming query params: " + isCompleted + " " + this.todoservice2.doSomething());
        return ResponseEntity.ok(todoList);
    }

//one way of
//    @PostMapping("/todos")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Todo createTodo(@RequestBody Todo newTodo){
//        todoList.add(newTodo);
//        return newTodo;
//    }


//    second way
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }


}
