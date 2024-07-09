package br.com.alura.literalura.main;

import br.com.alura.literalura.dto.ApiResponseDTO;
import br.com.alura.literalura.dto.BookDTO;
import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BookRepository;
import br.com.alura.literalura.service.ApiConsumer;
import br.com.alura.literalura.service.DataConverter;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiConsumer apiConsumer = new ApiConsumer();
    private final String ADDRESS = "https://gutendex.com/books/?search=";
    private final DataConverter converter = new DataConverter();
    private String bookTitle;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Main(){}

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    ================= LITERALURA =================
                                        
                    Escolha o número de sua opção:
                                        
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                                                   
                    0 - Sair
                    ==============================================
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addBookData();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação...");
                default:
                    System.out.println("Opção inválida.");

            }
        }
    }

    private void addBookData() {
        System.out.println(1);
        Book book = searchBookByTitleWeb();

        if (book == null){
            System.out.println("Não foi possível obter os dados deste livro na API.");
        }else{
            Author author = book.getAuthor();

            if (isBookRegistered(book.getTitle())) {
                System.out.println("O livro pesquisado já está cadastrado no sistema.");
            }else{
                Author authorOnDatabase = getAuthorOnDatabase(author.getName());

                if ( authorOnDatabase == null){
                    authorRepository.save(author);
                }else{
                    book.setAuthor(authorOnDatabase);
                }

                bookRepository.save(book);
                System.out.println(book);
            }
        }
    }

    private Book searchBookByTitleWeb(){
        System.out.println("Digite o nome do livro para a busca: ");
        String bookTitle = scanner.nextLine();

        var apiUrl = ADDRESS + bookTitle.replace(" ", "%20");
        var json = apiConsumer.getData(apiUrl);

        ApiResponseDTO apiResponse = converter.getData(json, ApiResponseDTO.class);
        Optional<BookDTO> searchedBook = apiResponse.bookData().stream().findFirst();

        if (searchedBook.isPresent()){
            BookDTO bookData = searchedBook.get();
            return new Book(bookData);
        }

        return null;
    }

    private boolean isBookRegistered(String bookTitle){
        Optional<Book> book = bookRepository.findByTitleContainingIgnoreCase(bookTitle);
        return book.isPresent();
    }

    private Author getAuthorOnDatabase(String authorName){
        Optional<Author> author = authorRepository.findByNameContainingIgnoreCase(authorName);
        return author.orElse(null);
    }


}
