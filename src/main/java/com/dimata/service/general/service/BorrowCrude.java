package com.dimata.service.general.service;

import com.dimata.service.general.dto.BorrowData;
import com.dimata.service.general.dto.mapper.BorrowMapper;
import com.dimata.service.general.model.entitiy.Borrow;
import com.dimata.service.general.repository.BorrowRepository;

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

    public Borrow create(BorrowData dto) {
        Objects.requireNonNull(dto);

        var borrow = BorrowMapper.INSTANCE.toEntity(dto);
        borrow.returnDate = calculateReturnDate(dto.getTerm(), dto.getBorrowDate());
        borrow.persist();

        return borrow;
    }

    public Borrow update(BorrowData dto, Long id) {
        Objects.requireNonNull(dto);
        return new Borrow();

    }

    public List<Borrow> listAll() {
        return borrowRepository.listAll();
    }

    public Borrow findById(Long id) {
        return borrowRepository.findByIdOptional(id)
                .map(Borrow.class::cast)
                .orElseThrow(() -> new NullPointerException("Book borrow not found"));
    }

    public void deleteById(long id) {
        borrowRepository.deleteById(id);
    }

    public Date calculateReturnDate(Integer term, Date borrowDate) {
        LocalDate newBorrowDate = borrowDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate returnDate = newBorrowDate.plusDays(term);

        Instant instant = returnDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }

}

