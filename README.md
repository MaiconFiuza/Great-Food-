# Great Food
Post Tech Fiap - Arquitetura e Desenvolvimento em JAVA -Tech Challenger Fase 1

## FIAP - Tech Challenge - Fase 1


### Sistema de gestão para seus estabelecimentos

Nessa primeira faze de entrega o objetivo é desenvolver um backend robusto utilizando Spring Boot para gerenciar usuários e atender aos requisitos definidos.

### Link para a documentação das API's do projeto (OpenAPI):
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Para executar a aplicação:

### 1. Fazer o build dos containeres da aplicação:
Executar o seguinte comando:
    
    docker compose build --no-cache

O comando acima gerará os conteineres de aplicação e banco de dados.

### 2. Executar a aplicação através dos containeres criados:
Executar o seguinte comando para inicializar os containeres da aplicação, na raíz do projeto (onde se encontra o arquivo docker-compose.yml):

    docker compose up

### 3. Acessar a aplicação
A aplicação estará disponível na seguinte URL:

    http://localhost:8080/

As collections do postman se encontram no seguinte link: [Collection Postman GREAT FOOD](https://github.com/MaiconFiuza/Great-Food-/blob/main/Projeto%20M%C3%B3dulo%201.postman_collection.json)
