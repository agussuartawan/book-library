package com.dimata.service.general.service;

import com.dimata.service.general.dto.BookData;
import com.dimata.service.general.dto.mapper.BookMapper;
import com.dimata.service.general.model.entitiy.Book;
import com.dimata.service.general.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookCrude {

    @Inject
    private BookRepository bookRepository;

    @Inject
    private Validator validator;

    public BookData create(BookData dto) {
        Objects.requireNonNull(dto);
        return bookRepository.save(new Book(), dto);
    }

    public BookData update(BookData dto, long id) {
        Objects.requireNonNull(dto);
        var book = bookRepository.findByIdOptional(id)
                .map(Book.class::cast)
                .orElseThrow(() -> new NullPointerException("Book not found."));

        return bookRepository.save(book, dto);
    }

    public List<BookData> listAll() {
        return bookRepository.streamAll()
                .map(BookMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public BookData findById(long id) {
        return BookMapper.INSTANCE.toDTO(bookRepository.findByIdOptional(id)
                .map(Book.class::cast)
                .orElseThrow(() -> new NullPointerException("Book not found")));
    }

    public List<BookData> search(String keyword) {
        return bookRepository.find(
                        "name LIKE CONCAT('%', ?1, '%') OR description LIKE CONCAT('%', ?1,'%') OR author LIKE CONCAT('%', ?1, '%')", keyword)
                .stream()
                .map(BookMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        bookRepository.delete(bookRepository.findByIdOptional(id)
                .map(Book.class::cast)
                .orElseThrow(() -> new NullPointerException("Book not found")));
    }

}
