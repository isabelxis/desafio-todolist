package br.com.isabelxavier.desafio_todolist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.isabelxavier.desafio_todolist.entity.Todo;
import lombok.experimental.var;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DesafioTodolistApplicationTests {

	@LocalServerPort
	private int port;

	@Test
	void testCreateSuccess() {
		var todo = new Todo(null,"Testar API", "Criar testes para a API de ToDo", false, 1);
		WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
		client.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].id").isNotEmpty()	
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testCreateFailure() {
		var todo = new Todo(null,"", "Criar testes para a API de ToDo", false, 1);
		WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
		client.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isBadRequest();
	}


}
