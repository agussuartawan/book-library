package com.dimata.service.general.model.body;

public record BorrowBody(
        Long bookId,
        Long memberId
) {

    public boolean isValid()
    {
        return bookId != null &&
                memberId != null;
    }

}
