package com.dimata.service.general.service;

import com.dimata.service.general.dto.BookData;
import com.dimata.service.general.model.entitiy.Book;
import com.dimata.service.general.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookCrude {

    @Inject
    private BookRepository bookRepository;

    @Inject
    private Validator validator;

    public BookData create(BookData dto)
    {
        Objects.requireNonNull(dto);
        return bookRepository.save(new Book(), dto);
    }

    public BookData update(BookData dto, long id)
    {
        Objects.requireNonNull(dto);
        var book = bookRepository.findByIdOptional(id)
                .map(Book.class::cast)
                .orElseThrow(() -> new NullPointerException("Book not found."));

        return bookRepository.save(book, dto);
    }

    public List<BookData> listAll()
    {
        return bookRepository.streamAll()
                .map(book -> {
                    BookData dto = new BookData();
                    dto.setId(book.getId());
                    dto.setName(book.getName());
                    dto.setDescription(book.getDescription());
                    dto.setAuthor(book.getAuthor());
                    dto.setCreatedAt(book.getCreatedAt());
                    dto.setUpdatedAt(book.getCreatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public BookData findById(long id)
    {
        var book = bookRepository.findByIdOptional(id)
                .map(Book.class::cast)
                .orElseThrow(() -> new NullPointerException("Book not found"));

        return new BookData(
                book.getId(),
                book.getName(),
                book.getDescription(),
                book.getAuthor(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

    public List<BookData> search(String keyword)
    {
        return bookRepository.find(
                "name LIKE CONCAT('%', ?1, '%') OR description LIKE CONCAT('%', ?1,'%') OR author LIKE CONCAT('%', ?1, '%')", keyword)
                .stream()
                .map(book -> {
                   BookData dto = new BookData();
                   dto.setId(book.getId());
                   dto.setName(book.getName());
                   dto.setDescription(book.getDescription());
                   dto.setAuthor(book.getAuthor());
                   dto.setCreatedAt(book.getCreatedAt());
                   dto.setUpdatedAt(book.getUpdatedAt());
                   return dto;
                })
                .collect(Collectors.toList());
    }

    public void deleteById(long id)
    {
        var book = bookRepository.findByIdOptional(id)
                .map(Book.class::cast)
                .orElseThrow(() -> new NullPointerException("Book not found"));
        bookRepository.delete(book);
    }

}
