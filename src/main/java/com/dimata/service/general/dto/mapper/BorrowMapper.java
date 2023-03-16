package com.dimata.service.general.dto.mapper;

import com.dimata.service.general.dto.BorrowData;
import com.dimata.service.general.model.entitiy.Book;
import com.dimata.service.general.model.entitiy.Borrow;
import com.dimata.service.general.model.entitiy.Member;
import com.dimata.service.general.repository.BookRepository;
import com.dimata.service.general.repository.MemberRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BookMapper.class, MemberMapper.class})
public interface BorrowMapper {

    BorrowMapper INSTANCE = Mappers.getMapper(BorrowMapper.class);

    @Mappings({
            @Mapping(source = "dto.bookId", target = "book", qualifiedByName = "bookIdToBook"),
            @Mapping(source = "dto.memberId", target = "member", qualifiedByName = "memberIdToMember")
    })
    Borrow toEntity(BorrowData dto);

    @Mapping(source = "bookId", target = "id")
    @Named("bookIdToBook")
    default Book bookIdToBook(Long bookId) {
        // TODO cara satu
        BookRepository bookRepository = new BookRepository();
        return bookRepository.findByIdOptional(bookId).orElseThrow(() -> new NullPointerException("Book not found"));
    }

    @Mapping(source = "memberId", target = "id")
    @Named("memberIdToMember")
    default Member memberIdToMember(Long memberId) {
        // TODO cara dua (inisialisasi objek lebih singkat)
        return new MemberRepository().findByIdOptional(memberId).orElseThrow(() -> new NullPointerException("Member not found"));
    }
}
