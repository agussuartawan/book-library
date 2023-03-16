package com.dimata.service.general.service;

import com.dimata.service.general.dto.ReturnData;
import com.dimata.service.general.dto.mapper.ReturnMapper;
import com.dimata.service.general.model.entitiy.Return;
import com.dimata.service.general.repository.ReturnRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ReturnCrude {

    @Inject
    ReturnRepository returnRepository;

    public Return create(ReturnData dto) {
        Objects.requireNonNull(dto);

        var returns = ReturnMapper.INSTANCE.toEntity(dto);
        returns.lateFee = calculateLateFee(ReturnMapper.INSTANCE.borrowIdToBorrow(dto.getBorrowId()).returnDate, returns.returnDate);
        returnRepository.persist(returns);

        return returns;
    }

    public List<Return> listAll() {
        return returnRepository.listAll();
    }

    public BigDecimal calculateLateFee(Date returnDateBorrow, Date returnDateReturn) {
        long daysBetween = ChronoUnit.DAYS.between(
                returnDateBorrow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                returnDateReturn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );

        if (daysBetween < 0) {
            return new BigDecimal(0);
        }

        return BigDecimal.valueOf(daysBetween).multiply(Return.LATE_FEE_PERDAY);
    }

    public Return findById(Long id) {
        return returnRepository.findByIdOptional(id)
                .orElseThrow(() -> new NullPointerException("Book return not found"));
    }

}
