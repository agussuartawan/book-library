package com.dimata.service.general.dto.mapper;

import com.dimata.service.general.dto.BookData;
import com.dimata.service.general.model.entitiy.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toEntity(BookData dto);

    BookData toDTO(Book book);
}
