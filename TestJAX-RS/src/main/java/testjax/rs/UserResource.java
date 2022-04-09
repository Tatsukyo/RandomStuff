package testjax.rs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Alexis TRAN
 */
@Path("/user")
public class UserResource {

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int pID) {
        return Response.status(Response.Status.OK).entity(pID << 2).build();
    }

    @POST
    public Response createUser(String pUser) {
        return Response.status(Response.Status.OK).entity(pUser).build();
    }
}
