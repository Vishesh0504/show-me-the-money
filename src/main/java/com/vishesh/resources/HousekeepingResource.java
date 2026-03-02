package com.vishesh.resources;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Tag(name = "Housekeeping Resource", description = "Resource for housekeeping tasks")
@Path("/housekeeping")
@Produces(MediaType.APPLICATION_JSON)

public class HousekeepingResource {

    @GET
    public Response helloWorld(){
        return Response.ok("Hello World!").build();
    }
}
