package com.dimata.service.general.model.body;

import java.util.Date;

public record BorrowBody(
        Long bookId,
        Long memberId,
        Date borrowDate,
        Integer term
) {

    public boolean isValid()
    {
        return bookId != null &&
                memberId != null &&
                borrowDate != null &&
                term != null;
    }

}
