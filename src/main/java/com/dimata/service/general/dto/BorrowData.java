package com.dimata.service.general.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BorrowData {

    @NotNull(message = "Book is required")
    private Long bookId;

    @NotNull(message = "Member is required")
    private Long memberId;

    @NotNull(message = "Borrow date is required")
    private Date borrowDate;

    @Min(value = 1, message = "Term must greater than zero")
    private Integer term;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
