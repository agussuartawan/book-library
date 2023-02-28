package com.dimata.service.general.controller;

import com.dimata.service.general.dto.BookData;
import com.dimata.service.general.dto.ResponseData;
import com.dimata.service.general.model.body.BookBody;
import com.dimata.service.general.model.entitiy.Book;
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
        ResponseData<BookData> responseData;

        Set<ConstraintViolation<BookData>> violations = validator.validate(dto);
        if(!violations.isEmpty())
        {
            responseData = new ResponseData<>(violations);
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("X-Reason", "validation-failed")
                    .entity(responseData)
                    .build();
        }

        responseData = new ResponseData<>(bookCrude.create(dto));
        return Response.ok(responseData).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Book update(BookBody body, @RestPath long id)
    {
        return bookCrude.update(body, id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public  void delete(@RestPath long id)
    {
        bookCrude.deleteById(id);
    }

    @GET
    @Path("/find")
    public List<Book> search(@RestQuery String keyword)
    {
        return bookCrude.search(keyword);
    }

    @GET
    public List<Book> getAll()
    {
        return bookCrude.listAll();
    }

    @GET
    @Path("/{id}")
    public Book findById(@RestPath long id)
    {
        return bookCrude.findById(id);
    }

}
