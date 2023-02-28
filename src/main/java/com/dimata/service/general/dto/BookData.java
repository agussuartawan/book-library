package com.dimata.service.general.dto;

import com.dimata.service.general.model.entitiy.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ApplicationScoped
public class BookData {

    private Long id;

    @NotBlank(message = "Book name cannot be empty")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Author cannot be empty")
    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BookData(Book book)
    {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();
    }
}
