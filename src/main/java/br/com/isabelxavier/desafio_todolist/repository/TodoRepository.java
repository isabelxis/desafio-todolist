package br.com.isabelxavier.desafio_todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.isabelxavier.desafio_todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
//atualizaprojeto
}
