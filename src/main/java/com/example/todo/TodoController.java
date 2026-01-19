package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Marks this class as a Spring REST controller.
 *
 * <p>
 * A controller acts as the entry point of the Spring framework.
 * It is responsible for receiving HTTP requests and sending HTTP responses.
 * </p>
 *
 * <p>
 * Controllers have two main responsibilities:
 * <ul>
 *   <li>Handling and processing incoming requests</li>
 *   <li>Returning appropriate HTTP responses</li>
 * </ul>
 * </p>
 *
 * <p>
 * When {@code @RestController} is used, Spring automatically serializes
 * the returned data and writes it to the HTTP response body.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    // This demonstrates composition: TodoController depends on TodoService
    @Autowired
    @Qualifier("fakeTodoService")
    private TodoService todoservice;

    // Acts as an in-memory data store for Todo objects
    public static List<Todo> todoList;

    public TodoController() {
        /**
         * TodoService todoservice
         *
         * This comment is intended to explain dependency creation.
         * However, this is NOT the Spring Boot way of creating objects.
         *
         * In Spring-based applications, dependencies should be injected
         * using constructor injection or field injection.
         *
         * The dependent class must be annotated with {@code @Component}
         * so that Spring can manage its lifecycle and inject it automatically.
         */
//        this.todoservice = todoservice;

        todoList = new ArrayList<>();
        todoList.add(new Todo(1, true, "MY", 231));
        todoList.add(new Todo(2, false, "Ms", 232));

        /**
         * This demonstrates manual object creation using plain Java.
         *
         * In this approach, the dependency is instantiated directly
         * instead of being injected by Spring.
         *
         * Example of manual instantiation:
         * this.todoService = new TodoService();
         *
         * This approach should be avoided in Spring applications.
         */
    }

    /**
     * Returns the complete list of todos.
     */
    @GetMapping
    public List<Todo> getTodoList() {
        return todoList;
    }

    /**
     * Returns todos based on query parameters.
     *
     * @param isCompleted optional query parameter to filter todos
     */
    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable Long todoId) {
        for (Todo todo : todoList) {
            if (todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        // HW: Along with 404 status code, try to send a json {message: Todo not found}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }


    /**
     * Creates a new Todo and returns it with HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newTodo);
    }
}
