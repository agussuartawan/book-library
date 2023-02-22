package com.dimata.service.general.service;

import com.dimata.service.general.model.body.BookBody;
import com.dimata.service.general.model.entitiy.Book;
import com.dimata.service.general.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class BookCrude {

    @Inject
    BookRepository bookRepository;

    public Book create(BookBody body)
    {
        Objects.requireNonNull(body);

        if(!body.isValid()){
            throw  new IllegalArgumentException("Book not valid!");
        }

        var book = new Book();
        book.name = body.name();
        book.description = body.description();
        book.author = body.author();
        book.persist();

        return book;
    }

    public Book update(BookBody body, long id)
    {
        Objects.requireNonNull(body);

        var book = Book.findByIdOptional(id)
                .map(Book.class::cast)
                .orElseThrow(() -> new NullPointerException("Book not found."));

        if(body.name() != null){
            book.name = body.name();
        }

        if (body.description() != null){
            book.description = body.description();
        }

        if (body.author() != null){
            book.author = body.author();
        }

        return book;
    }

    public List<Book> listAll()
    {
        return bookRepository.listAll();
    }

    public Book findById(long id)
    {
        return bookRepository.findById(id);
    }

    public List<Book> search(String keyword)
    {
        return bookRepository.find(
                "name LIKE CONCAT('%', ?1, '%') OR description LIKE CONCAT('%', ?1,'%') OR author LIKE CONCAT('%', ?1, '%')", keyword
        ).list();
    }

    public void deleteById(long id)
    {
        bookRepository.deleteById(id);
    }

}
