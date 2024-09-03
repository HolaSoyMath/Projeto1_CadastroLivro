package com.matchola.projeto1.controllers;

import com.matchola.projeto1.dtos.BookRecordDto;
import com.matchola.projeto1.models.BookModel;
import com.matchola.projeto1.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // Cadastrar um livro
    @PostMapping("/books")

    // ResponseEntity<BookModel> = A resposta deverá ser um BookModel
    // saveBook = Será criado o método saveBook
    // @RequestBody = receberá as informações pelo corpo da requisição
    // @Valid = Força as validações especificadas no Dto
    // BookRecordDto = é o Dto que estou utilizando para validar as informações
    // book = é a variável que receberá os valores da requisição
    public ResponseEntity<BookModel> saveBook(@RequestBody @Valid BookRecordDto book) {
        var bookModel = new BookModel();
        BeanUtils.copyProperties(book, bookModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(bookModel));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }

//    @Operation(summary = "Obter todos os livros de um autor", method = "GET")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Livros retornados"),
//            @ApiResponse(responseCode = "500", description = "Erro do servidor")
//    })
//    @GetMapping(value = "/books/author/{author}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)






    @GetMapping("/books/author/{author}")
    // Em @PathVariable colocamos dentro de value o nome do valor que vamos pegar da URI
    public ResponseEntity<Object> getBookByAuthor(@PathVariable(value="author") String author) {
        // Dentro do Book Repository tive que criar o "Método" find by author
        List<BookModel> books = bookRepository.findByAuthor(author);
        if (books.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }








//    @Operation(summary = "Obter todos os livros de um título", method = "GET")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Livros retornados"),
//            @ApiResponse(responseCode = "500", description = "Erro do servidor")
//    })
//    @GetMapping(value = "/books/title/{title}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/books/title/{title}")

    // Em @PathVariable colocamos dentro de value o nome do valor que vamos pegar da URI
    public ResponseEntity<Object> getBookByTitle(@PathVariable(value="title") String title) {
        // Dentro do Book Repository tive que criar o "Método" find by title
        List<BookModel> books = bookRepository.findByTitle(title);
        if (books.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Título não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
