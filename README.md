# Desafio - Sistema de Gerenciamento de Tarefas (To-Do List)

Aplicação backend para gerenciamento de tarefas desenvolvida como desafio técnico. O projeto implementa uma API REST completa para criar, listar, atualizar e deletar tarefas.

Referência do desafio: [Desafio Junior Backend - Simplify](https://github.com/simplify-tec/desafio-junior-backend-simplify)

## 📋 Sobre o Projeto

Este é um sistema de To-Do List que permite aos usuários gerenciar suas tarefas de forma simples e eficiente. A aplicação organiza as tarefas por prioridade e ordem alfabética.

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 4.0.2** - Framework web
- **Spring Data JPA** - Persistência de dados
- **MySQL** - Banco de dados relacional
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciador de dependências

## 📦 Funcionalidades

A aplicação oferece os seguintes endpoints:

- **POST /todos** - Criar uma nova tarefa
- **GET /todos** - Listar todas as tarefas (ordenadas por prioridade e nome)
- **PUT /todos** - Atualizar uma tarefa existente
- **DELETE /todos/{id}** - Deletar uma tarefa

## 📝 Modelo de Dados

### Todo

- `id` (Long) - Identificador único gerado automaticamente
- `nome` (String) - Nome/título da tarefa
- `descricao` (String) - Descrição detalhada da tarefa
- `realizado` (Boolean) - Status de conclusão da tarefa
- `prioridade` (Integer) - Nível de prioridade da tarefa

## 🚀 Como Executar

### Pré-requisitos

- Java 17+
- MySQL Server
- Maven

### Passos para Execução

1. Clone o repositório
   ```bash
   git clone <url-do-repositorio>
   cd desafio-todolist
   ```

2. Configure o banco de dados no arquivo `application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/todolist
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. Execute a aplicação
   ```bash
   mvn spring-boot:run
   ```

   Ou compile e execute o JAR:
   ```bash
   mvn clean package
   java -jar target/desafio-todolist-0.0.1-SNAPSHOT.jar
   ```

A aplicação estará disponível em `http://localhost:8080`

## 📂 Estrutura do Projeto

```
src/main/java/br/com/isabelxavier/desafio_todolist/
├── controller/
│   └── TodoController.java      - Endpoints da API
├── entity/
│   └── Todo.java                - Modelo de dados
├── repository/
│   └── TodoRepository.java      - Acesso aos dados
├── service/
│   └── TodoService.java         - Lógica de negócio
└── DesafioTodolistApplication.java - Classe principal
```

## 💡 Notas Importantes

- As tarefas são automaticamente ordenadas por prioridade (decrescente) e nome (crescente)
- A data é uma sugestão da versão do projeto: 8 de fevereiro de 2026

## 📄 Licença

Este projeto é um desafio técnico educacional.
