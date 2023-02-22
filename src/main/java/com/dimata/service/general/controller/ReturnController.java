package com.dimata.service.general.controller;

import com.dimata.service.general.model.body.ReturnBody;
import com.dimata.service.general.model.entitiy.Return;
import com.dimata.service.general.service.ReturnCrude;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api/returns")
public class ReturnController {

    @Inject
    ReturnCrude returnCrude;

    @POST
    public Return create(ReturnBody body)
    {
        return returnCrude.create(body);
    }

}
