package com.dimata.service.general.repository;

import com.dimata.service.general.dto.BookData;
import com.dimata.service.general.dto.mapper.BookMapper;
import com.dimata.service.general.model.entitiy.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public BookData save(Book book, BookData dto) {
        book = BookMapper.INSTANCE.toEntity(dto);
        book.persistAndFlush();
        return BookMapper.INSTANCE.toDTO(book);
    }

}
