package com.example.todo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.ArrayList;

@RestController
//by adding this annotations in the class , spring framework will treat it as controller class
//controller means that it is the entry point of the framework
//controller have only two use cases collecting the request nd sending the response
//it is automatically going to return the response in the https response body
//when we have wrote the restcontroller its start collecting the response and request
public class TodoController {
    public static List<Todo> todoList ;

    public TodoController() {
        todoList=new ArrayList<>();
        todoList.add(new Todo(1,true,"MY",231));
        todoList.add(new Todo(2,false,"Ms",232));
    }
    @GetMapping("/todos")
    public List<Todo>  getTodoList() {
        return todoList;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return newTodo;
    }

}
