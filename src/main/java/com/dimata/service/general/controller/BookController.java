package com.dimata.service.general.controller;

import com.dimata.service.general.model.body.BookBody;
import com.dimata.service.general.model.entitiy.Book;
import com.dimata.service.general.service.BookCrude;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/books")
public class BookController
{

    @Inject
    BookCrude bookCrude;

    @POST
    @Transactional
    public Book create(BookBody body)
    {
        return bookCrude.create(body);
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
