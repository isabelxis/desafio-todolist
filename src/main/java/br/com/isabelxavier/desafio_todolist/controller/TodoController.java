package br.com.isabelxavier.desafio_todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isabelxavier.desafio_todolist.entity.Todo;
import br.com.isabelxavier.desafio_todolist.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
@NoArgsConstructor
public class TodoController {
    private TodoService todoService;

    @PostMapping
    List<Todo> create(@RequestBody Todo todo) {

        return todoService.create(todo);

    }

    @GetMapping
    List<Todo> list(){

        return todoService.list();
    }

    @PutMapping
    List<Todo> update(@RequestBody Todo todo){

        return todoService.update(todo);    
        
    }

    @DeleteMapping("/{id}")
    List<Todo> delete(@PathVariable Long id){

        return todoService.delete(id);
        
    }


}
