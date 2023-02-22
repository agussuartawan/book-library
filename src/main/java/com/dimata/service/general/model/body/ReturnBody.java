package com.dimata.service.general.model.body;

import java.util.Date;

public record ReturnBody(
        Long borrowId,
        Date returnDate
) {

    public boolean isValid()
    {
        return borrowId != null &&
                returnDate != null;
    }

}
