package com.dimata.service.general.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ReturnData {

    @NotNull(message = "Borrow is required")
    private Long borrowId;

    @NotNull(message = "Return date is required")
    private Date returnDate;

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
