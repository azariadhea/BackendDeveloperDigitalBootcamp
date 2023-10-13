package com.multipolar.bootcamp.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloworldController {

    //http://localhost:8080/api/hello
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello World");
    }

    //http://localhost:8080/api/name?yourName=Aza
    @GetMapping("/name")
    public ResponseEntity<String> helloName(@RequestParam String yourName){
        return ResponseEntity.ok("Hello " + yourName);
    }

    //http://localhost:8080/api/name/Aza
    @GetMapping("/name/{yourName}")
    public ResponseEntity<String> helloNameSegment(@PathVariable String yourName){
        return ResponseEntity.ok("Hello " + yourName);
    }

    //http://localhost:8080/api/todos
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> todos(){
        List<Todo> todoList = List.of(
                new Todo( 1, "Learning"),
                new Todo( 2, "Coding"),
                new Todo( 3, "Monetizing"));
        return ResponseEntity.ok(todoList);
    }
}
