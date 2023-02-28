package com.dimata.service.general.controller;

import com.dimata.service.general.dto.ResponseData;
import com.dimata.service.general.model.body.BorrowBody;
import com.dimata.service.general.model.entitiy.Borrow;
import com.dimata.service.general.service.BorrowCrude;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/borrows")
public class BorrowController {

    @Inject
    BorrowCrude borrowCrude;
    @Inject
    ResponseData responseData;

    @POST
    @Transactional
    public Borrow create(BorrowBody body)
    {
        return borrowCrude.create(body);
    }

    /*@GET
    public ResponseData getAll()
    {
        responseData.status = true;
        responseData.messages = "Book borrow data.";
        responseData.payload = borrowCrude.listAll();
        return responseData;
    }*/

}
