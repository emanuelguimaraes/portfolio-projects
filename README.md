# Portfolio Projects - Gerenciamento de Portfólio de Projetos

Este projeto é uma aplicação web Spring Boot para gerenciamento de um portfólio de projetos. O sistema permite o cadastro, consulta, edição e exclusão de projetos e pessoas, além da associação de membros a projetos.

## Pré-requisitos (com Docker e Docker Compose)

Para executar a aplicação usando Docker e Docker Compose, você precisa ter o Docker e o Docker Compose instalados em sua máquina.  As instruções de instalação podem ser encontradas na documentação oficial do Docker: [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/)

## Executando a aplicação

1. Clone o repositório

```bash
git clone https://github.com/emanuelguimaraes/portfolio-projects.git
```

2. Navegue até o diretório do projeto

```bash
cd portfolio-projects
```

3. Execute o comando *docker-compose up -d*
```bash
docker-compose up -d
```

Isso irá construir a imagem Docker da aplicação e iniciar os containers necessários (aplicação e banco de dados PostgreSQL). A aplicação estará disponível em http://localhost:8080.

## Endpoints da API

A aplicação expõe os seguintes endpoints para gerenciamento de pessoas (formato JSON):

**Adicionar Pessoa:**

* **URL:** `/pessoas`
* **Método:** `POST`
* **Corpo da requisição (JSON):**

```json
{
  "nome": "Nome da Pessoa",
  "atribuicao": "FUNCIONARIO" // ou "GERENTE"
}
```

**Excluir Pessoa:**

* **URL:** `/pessoas/{id}`
* **Método:** `DELETE`
* **Parâmetros:**
    * `{id}`: ID da pessoa a ser excluída.

**Observação:**  Os endpoints de projeto são acessíveis pela interface web.

## Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* PostgreSQL
* JSP
* Bootstrap 5
* JUnit
* Maven
* Docker
* Docker Compose