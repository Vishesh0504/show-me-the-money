package com.vishesh.resources;

import com.vishesh.auth.User;
import io.dropwizard.auth.Auth;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
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
    @Path("/hello")
    public Response helloWorld(){
        return Response.ok("Hello World!").build();
    }

    @GET
    @Path("/testAuth")
    @RolesAllowed("USER")
    public Response testAuth(@Auth User user){
        return Response.ok(user).build();
    }
}
