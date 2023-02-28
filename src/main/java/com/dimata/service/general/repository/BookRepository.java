package com.dimata.service.general.repository;

import com.dimata.service.general.dto.BookData;
import com.dimata.service.general.model.entitiy.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public BookData save(Book book, BookData dto)
    {
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        book.persistAndFlush();

        return new BookData(book);
    }

}
