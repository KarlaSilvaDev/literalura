![Imgur](https://i.imgur.com/s7BLsQO.png)

# Literalura

Literalura consiste em uma aplicação para a construção de um catálogo de livros utilizando Java, Spring, PostgreSQL e a API Gutendex, que possui dados de mais de 70 mil livros.

A interação é feita inteiramente via linha de comando. Esta aplicação permite buscar seus livros favoritos na API Gutendex e registrar os dados obtidos, incluindo dados referentes aos autores, no banco de dados da Literalura. Além disso, é possível listar todos os livros e autores registrados, bem como filtrar os livros por idioma e listar apenas os autores vivos em um determinado ano. 

## Funcionalidades

1. **Buscar livro pelo título**: Consulta a API Gutendex para buscar livros pelo título em inglês.
2. **Listar livros registrados**: Exibe todos os livros registrados no banco de dados.
3. **Listar autores registrados**: Exibe todos os autores registrados no banco de dados.
4. **Listar autores vivos em um determinado ano**: Lista os autores vivos em um ano especificado pelo usuário.
5. **Listar livros em um determinado idioma**: Lista livros registrados no banco de dados em um idioma especificado via código do idioma (Se for informado o código "en", serão listados os livros em inglês registrados no banco de dados).

## Tecnologia Utilizadas 

- Java 21
- Spring Boot
- Hibernate
- PostgreSQL
- Gutendex API
- Maven

## Como executar o projeto 

1. Clone o repositório para a sua máquina local.
    ```bash
    git clone https://github.com/KarlaSilvaDev/literalura.git
    ```
    
2. Entre no diretório do projeto
    ```bash
    cd literalura
    ```
    
3. Configure o banco de dados no arquivo `application.properties` ou cria variáveis de ambiente na sua máquina:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
    spring.datasource.username=seu-usuario
    spring.datasource.password=sua-senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```
    
4. Execute a aplicação.
    ```bash
    mvn spring-boot:run
    ```
