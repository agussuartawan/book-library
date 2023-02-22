package com.dimata.service.general.service;

import com.dimata.service.general.model.body.ReturnBody;
import com.dimata.service.general.model.entitiy.Borrow;
import com.dimata.service.general.model.entitiy.Return;
import com.dimata.service.general.repository.ReturnRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Objects;

@ApplicationScoped
public class ReturnCrude {

    @Inject
    ReturnRepository returnRepository;

    public Return create(ReturnBody body)
    {
        Objects.requireNonNull(body);

        if(!body.isValid())
        {
            throw new IllegalArgumentException("Return not valid.");
        }

        var borrow = Borrow.findByIdOptional(body.borrowId())
                .map(Borrow.class::cast)
                .orElseThrow();

        var returns = new Return();
        returns.borrow = borrow;
        returns.returnDate = body.returnDate();
        returns.lateFee = calculateLateFee(borrow);
        returns.persist();

        return returns;
    }

    public BigDecimal calculateLateFee(Borrow borrow)
    {
        BigDecimal lateFee = new BigDecimal(0);

        return lateFee;
    }

}
