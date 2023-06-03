package com.example.demolearnjpa.controller;

import com.example.demolearnjpa.entity.Todo;
import com.example.demolearnjpa.repository.TodoRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoController {

    private TodoRepository todoRepository;
    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("TodoList", todoRepository.findAll());
        return "index";
    }

    @PostMapping("/api/v1/todos")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        Todo newTodo = new Todo(null, todo.getTitle(), false);
        todoRepository.save(newTodo);
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED); //return 201
    }

    @PutMapping("/api/v1/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        //check id
        Todo updateTodo = todoRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found");
                });
        //update
        updateTodo.setTitle(todo.getTitle());
        updateTodo.setStatus(todo.getStatus());
        todoRepository.save(updateTodo);
        return  ResponseEntity.ok(updateTodo);
    }

    @DeleteMapping("/api/v1/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Integer id) {
        //check id
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found");
                });
        todoRepository.delete(todo);
        return ResponseEntity.noContent().build(); //return 204
    }

}
