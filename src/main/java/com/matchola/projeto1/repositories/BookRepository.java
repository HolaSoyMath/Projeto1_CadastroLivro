package com.matchola.projeto1.repositories;

import com.matchola.projeto1.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// Em JpaRepository deve-se indicar o tipo da entidade e qual o tipo da variável do ID
public interface BookRepository extends JpaRepository<BookModel, Long> {

    // Indico que estou retornando uma Lista do tipo bookModel e o "método" é o find by author que usa como parâmetro na query a coluna de String author
    List<BookModel> findByAuthor(String author);

    // Indico que estou retornando uma Lista do tipo bookModel e o "método" é o find by author que usa como parâmetro na query a coluna de String Title
    List<BookModel> findByTitle(String title);
}
