package com.dimata.service.general.service;

import com.dimata.service.general.model.body.BorrowBody;
import com.dimata.service.general.model.entitiy.Book;
import com.dimata.service.general.model.entitiy.Borrow;
import com.dimata.service.general.model.entitiy.Member;
import com.dimata.service.general.repository.BookRepository;
import com.dimata.service.general.repository.BorrowRepository;
import com.dimata.service.general.repository.MemberRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class BorrowCrude {

    @Inject
    BorrowRepository borrowRepository;
    @Inject
    BookRepository bookRepository;
    @Inject
    MemberRepository memberRepository;

    public Borrow create(BorrowBody body)
    {
        Objects.requireNonNull(body);

        if(!body.isValid())
        {
            throw new IllegalArgumentException("Borrow not valid.");
        }

        var book = bookRepository.findByIdOptional(body.bookId())
                .map(Book.class::cast)
                .orElseThrow();
        var member = memberRepository.findByIdOptional(body.memberId())
                .map(Member.class::cast)
                .orElseThrow();

        var borrow = new Borrow();
        borrow.book = book;
        borrow.member = member;
        borrow.borrowDate = body.borrowDate();
        borrow.term = body.term();
        borrow.returnDate = calculateReturnDate(body.term(), body.borrowDate());
        borrow.persist();

        return borrow;
    }

    public List<Borrow> listAll()
    {
        return borrowRepository.listAll();
    }

    public Date calculateReturnDate(Integer term, Date borrowDate)
    {
        LocalDate newBorrowDate = borrowDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate returnDate = newBorrowDate.plusDays(term);

        Instant instant = returnDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }

}

