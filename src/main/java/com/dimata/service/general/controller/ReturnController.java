package com.dimata.service.general.controller;

import com.dimata.service.general.dto.ResponseData;
import com.dimata.service.general.dto.ReturnData;
import com.dimata.service.general.model.entitiy.Return;
import com.dimata.service.general.service.ReturnCrude;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/returns")
public class ReturnController {

    @Inject
    ReturnCrude returnCrude;

    @POST
    @Transactional
    public Response create(ReturnData dto) {
        return Response.ok(
                new ResponseData<>(
                        true,
                        List.of("Book return created successfully"),
                        List.of(returnCrude.create(dto))
                )
        ).build();
    }

    @GET
    public ResponseData<Return> listAll() {
        return new ResponseData<>(
                true,
                List.of("Book return list"),
                returnCrude.listAll()
        );
    }

}
