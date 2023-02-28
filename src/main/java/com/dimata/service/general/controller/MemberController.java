package com.dimata.service.general.controller;

import com.dimata.service.general.dto.MemberData;
import com.dimata.service.general.dto.ResponseData;
import com.dimata.service.general.model.body.MemberBody;
import com.dimata.service.general.model.entitiy.Member;
import com.dimata.service.general.service.MemberCrude;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArray;
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

@Path("/api/members")
public class MemberController {

    @Inject
    private MemberCrude memberCrude;
    @Inject
    private Validator validator;

    @POST
    @Transactional
    public Response create(MemberData dto)
    {
        Set<ConstraintViolation<MemberData>> violations = validator.validate(dto);
        if(!violations.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("X-Reason", "validation-failed")
                    .entity(new ResponseData<>(violations))
                    .build();
        }

        return Response.ok(
            new ResponseData<>(memberCrude.create(dto))
        ).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(MemberData dto, @RestPath long id)
    {
        Set<ConstraintViolation<MemberData>> violations = validator.validate(dto);
        if(!violations.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("X-Reason", "validation-failed")
                    .entity(new ResponseData<>(violations))
                    .build();
        }

        return Response.ok(
                new ResponseData<>(memberCrude.update(dto, id))
        ).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@RestPath long id)
    {
        memberCrude.deleteById(id);
        return Response.ok(
                new ResponseData<>(
                        true,
                        List.of("Member deleted successfully"),
                        null
                )
        ).build();
    }

    @GET
    @Path("/find")
    public Response search(@RestQuery String keyword)
    {
        return Response.ok(
                new ResponseData<>(
                        true,
                        List.of("Search result for member table"),
                        memberCrude.search(keyword)
                )
        ).build();
    }

    @GET
    public Response getAll()
    {
        return Response.ok(
                new ResponseData<>(
                        true,
                        List.of("List of members"),
                        memberCrude.listAll()
                )
        ).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@RestPath long id)
    {
        return Response.ok(
                new ResponseData<>(
                        true,
                        List.of("Member filtered by id"),
                        List.of(memberCrude.findById(id))
                )
        ).build();
    }

}
