package com.dimata.service.general.controller;

import com.dimata.service.general.dto.BorrowData;
import com.dimata.service.general.dto.ResponseData;
import com.dimata.service.general.service.BorrowCrude;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/api/borrows")
public class BorrowController {

    @Inject
    BorrowCrude borrowCrude;
    @Inject
    Validator validator;

    @POST
    @Transactional
    public Response create(BorrowData dto) {
        Set<ConstraintViolation<BorrowData>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResponseData<>(violations))
                    .build();
        }

        return Response.ok(
                new ResponseData<>(borrowCrude.create(dto))
        ).build();
    }

    @GET
    public Response getAll() {
        return Response.ok(
                new ResponseData<>(
                        true,
                        List.of("Book list"),
                        borrowCrude.listAll()
                )
        ).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@RestPath Long id) {
        return Response.ok(
                new ResponseData<>(
                        true,
                        List.of("List borrow filtered by id"),
                        List.of(borrowCrude.findById(id))
                )
        ).build();
    }

}
