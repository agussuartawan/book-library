package com.dimata.service.general.service;

import com.dimata.service.general.model.body.ReturnBody;
import com.dimata.service.general.model.entitiy.Book;
import com.dimata.service.general.model.entitiy.Borrow;
import com.dimata.service.general.model.entitiy.Member;
import com.dimata.service.general.model.entitiy.Return;
import com.dimata.service.general.repository.BookRepository;
import com.dimata.service.general.repository.BorrowRepository;
import com.dimata.service.general.repository.MemberRepository;
import com.dimata.service.general.repository.ReturnRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ReturnCrude {

    @Inject
    ReturnRepository returnRepository;
    @Inject
    BorrowRepository borrowRepository;
    @Inject
    BookRepository bookRepository;
    @Inject
    MemberRepository memberRepository;

    public Return create(ReturnBody body)
    {
        Objects.requireNonNull(body);

        if(!body.isValid())
        {
            throw new IllegalArgumentException("Return not valid.");
        }

        var borrow = borrowRepository.findByIdOptional(body.borrowId())
                .map(Borrow.class::cast)
                .orElseThrow();

        borrow.book = bookRepository.findByIdOptional(borrow.book.getId())
                .map(Book.class::cast)
                .orElseThrow();
        borrow.member = memberRepository.findByIdOptional(borrow.member.id)
                .map(Member.class::cast)
                .orElseThrow();

        var returns = new Return();
        returns.borrow = borrow;
        returns.returnDate = body.returnDate();
        returns.lateFee = calculateLateFee(borrow.returnDate, returns.returnDate);
        returns.persist();

        return returns;
    }

    public List<Return> listAll()
    {
        return returnRepository.listAll();
    }

    public BigDecimal calculateLateFee(Date returnDateBorrow, Date returnDateReturn)
    {
        BigDecimal lateFee = new BigDecimal(0);
        LocalDate newReturnDateBorrow = returnDateBorrow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newReturnDateReturn = returnDateReturn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long daysBetween = ChronoUnit.DAYS.between(newReturnDateBorrow, newReturnDateReturn);

        if(daysBetween > 0)
        {
            lateFee = BigDecimal.valueOf(daysBetween).multiply(Return.LATE_FEE_PERDAY);
        }

        return lateFee;
    }

}
