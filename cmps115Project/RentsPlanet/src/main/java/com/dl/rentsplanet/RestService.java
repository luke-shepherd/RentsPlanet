package com.dl.rentsplanet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Created by bicboi on 10/13/16.
 */
@Path("/rest")
public class RestService {
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEntry(RentEntryBean reb){
        try{
            RentBL rb = new RentBL();
            rb.addRentEntry(reb);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Response.status(200).build();
    }
}
