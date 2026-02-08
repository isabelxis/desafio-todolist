package br.com.isabelxavier.desafio_todolist.service;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.isabelxavier.desafio_todolist.entity.Todo;
import br.com.isabelxavier.desafio_todolist.repository.TodoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoService {

    private TodoRepository todoRepository;

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by( "prioridade").descending()
                .and(Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }



}
