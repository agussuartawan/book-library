package com.dimata.service.general.controller;

import com.dimata.service.general.model.body.MemberBody;
import com.dimata.service.general.model.entitiy.Member;
import com.dimata.service.general.service.MemberCrude;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/members")
public class MemberController {

    @Inject
    MemberCrude memberCrude;

    @POST
    @Transactional
    public Member create(MemberBody body)
    {
        return memberCrude.create(body);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Member update(MemberBody body, @RestPath long id)
    {
        return memberCrude.update(body, id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@RestPath long id)
    {
        Member.deleteById(id);
    }

    @GET
    @Path("/find")
    public List<Member> search(@RestQuery String keyword)
    {
        return memberCrude.search(keyword);
    }

    @GET
    public List<Member> getAll()
    {
        return memberCrude.listAll();
    }

}
