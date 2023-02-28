package com.dimata.service.general.service;

import com.dimata.service.general.dto.BookData;
import com.dimata.service.general.model.body.BookBody;
import com.dimata.service.general.model.entitiy.Book;
import com.dimata.service.general.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.*;

@ApplicationScoped
public class BookCrude {

    @Inject
    private BookRepository bookRepository;

    @Inject
    private Validator validator;

    public BookData create(BookData dto)
    {
        Objects.requireNonNull(dto);
        return bookRepository.create(dto);
    }

    public Book update(BookBody body, long id)
    {
        Objects.requireNonNull(body);

        var book = Book.findByIdOptional(id)
                .map(Book.class::cast)
                .orElseThrow(() -> new NullPointerException("Book not found."));

        if(body.name() != null){
            book.setName(body.name());
        }

        if (body.description() != null){
            book.setDescription(body.description());
        }

        if (body.author() != null){
            book.setAuthor(body.author());
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
                "name LIKE CONCAT('%', ?1, '%') OR description LIKE CONCAT('%', ?1,'%') OR author LIKE CONCAT('%', ?1, '%')"
                , keyword).list();
    }

    public void deleteById(long id)
    {
        bookRepository.deleteById(id);
    }

}
