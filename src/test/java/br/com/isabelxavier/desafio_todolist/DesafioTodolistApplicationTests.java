package br.com.isabelxavier.desafio_todolist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.jayway.jsonpath.JsonPath;

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

	@Test
	void testList() {
		WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
		client.get()
			.uri("/todos")
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray();
	}

	@Test
	void testUpdateSuccess() {
		WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
		// Criar um todo
		var todo = new Todo(null,"Testar API", "Criar testes para a API de ToDo", false, 1);
		String createResponse = client.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class)
			.returnResult()
			.getResponseBody();

		Integer id = JsonPath.read(createResponse, "$[0].id");		
		// Atualizar o todo
		var updatedTodo = new Todo(id.longValue(), "Testar API Update", "Criar testes para a API de ToDo Update", true, 1);
		client.put()
			.uri("/todos")
			.bodyValue(updatedTodo)
			.exchange()
			.expectBody(String.class)
			.returnResult();
		
	}

	@Test
	void testUpdateFailure() {
		WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();

		var todo = new Todo(null,"Testar API", "Criar testes para a API de ToDo", false, 1);
		String createResponse = client.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class)
			.returnResult()
			.getResponseBody();
		
		Integer id = JsonPath.read(createResponse, "$[0].id");		

		var updatedTodo = new Todo(id.longValue(), "", "", true, 1);
		client.put()
			.uri("/todos")
			.bodyValue(updatedTodo)
			.exchange()
			.expectStatus().isBadRequest();
		
	}

	@Test
	void testDelete() {
		WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();

		var todo = new Todo(null,"Testar API", "Criar testes para a API de ToDo", false, 1);
		String createResponse = client.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class)
			.returnResult()
			.getResponseBody();

	}

}
