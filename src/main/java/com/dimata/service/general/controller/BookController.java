package com.dimata.service.general.controller;

import com.dimata.service.general.dto.BookData;
import com.dimata.service.general.dto.ResponseData;
import com.dimata.service.general.service.BookCrude;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/api/books")
public class BookController
{

    @Inject
    private BookCrude bookCrude;
    @Inject
    private Validator validator;

    @POST
    @Transactional
    public Response create(BookData dto)
    {
        Set<ConstraintViolation<BookData>> violations = validator.validate(dto);
        if(!violations.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("X-Reason", "validation-failed")
                    .entity(new ResponseData<>(violations))
                    .build();
        }

        return Response.ok(
                new ResponseData<>(bookCrude.create(dto))
        ).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(BookData dto, @RestPath long id)
    {
        Set<ConstraintViolation<BookData>> violations = validator.validate(dto);
        if(!violations.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("X-Reason", "validation-failed")
                    .entity(new ResponseData<>(violations))
                    .build();
        }

        return Response.ok(
                new ResponseData<>(bookCrude.update(dto, id))
        ).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@RestPath long id)
    {
        bookCrude.deleteById(id);
        return Response.ok(
            new ResponseData<>(true, List.of("Book deleted successfully"), null)
        ).build();
    }

    @GET
    @Path("/find")
    public Response search(@RestQuery String keyword)
    {
        return Response.ok(
                new ResponseData<>(true, List.of("Search result in books database"), bookCrude.search(keyword))
        ).build();
    }

    @GET
    public Response getAll()
    {
       return Response.ok(
               new ResponseData<>(true, List.of("List of all books"), bookCrude.listAll())
       ).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@RestPath long id)
    {
        return Response.ok(
                new ResponseData<>(
                        true,
                        List.of("Book filtered by id"),
                        List.of(bookCrude.findById(id))
                )
        ).build();
    }

}
