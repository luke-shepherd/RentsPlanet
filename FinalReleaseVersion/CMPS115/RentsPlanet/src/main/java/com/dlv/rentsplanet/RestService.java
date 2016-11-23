package com.dlv.rentsplanet;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.URLDecoder;
import java.lang.*;

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


    @POST
    @Path("/getinfo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getInfo(@QueryParam(value = "address") final String address) {
        try {
           
           //write to input file
           PrintWriter pw = new PrintWriter("/root/Scraping/addr_file.in");
           pw.println(address);
           pw.close();

            ProcessBuilder pb = new ProcessBuilder("/usr/bin/python", "/root/Scraping/TruScrape.py");
            pb.redirectErrorStream(true); // equivalent of 2>&1
            Process p = pb.start();
            p.waitFor();

           //run scraping script
           //Runtime.getRuntime().exec("python /root/Scraping/TruScrape.py");

           List<String> info_list = new ArrayList<String> ();
           File outfile = new File("/root/Scraping/addr_file.out");
           Scanner scan = new Scanner(outfile);

           while(scan.hasNextLine()) {
              info_list.add(scan.nextLine());
           }

           return Response.status(Response.Status.OK).entity(info_list).type(MediaType.APPLICATION_JSON).build();
           
 
        } catch (Exception e) {
           //error do something
           e.printStackTrace();
        } finally {
            int noContent = 204;
            String message = "get rekd2";
            return Response.status(noContent).entity(message).type("text/plain").build();
        }
   }



    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEntry(RentEntryBean reb){
        try{
            RentBL rentBL = new RentBL();
            rentBL.deleteHouseEntry(reb);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Response.status(200).build();
    }


    @GET
    @Path("/display")
    @Produces(MediaType.APPLICATION_JSON)
    public Response displayAll(){
        List<RentEntryBean> rentEntries = new ArrayList<RentEntryBean>();
        try{
            RentBL rentBL = new RentBL();
            rentEntries = rentBL.listHouses();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        // Handle type erasure
        GenericEntity<List<RentEntryBean>> list = new GenericEntity<List<RentEntryBean>>(rentEntries) {};
        return Response.status(Response.Status.OK).entity(list).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntry(RentEntryBean reb){
        try{
            RentBL rentBL = new RentBL();
            rentBL.updateHouseEntry(reb);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Response.status(200).build();
    }

    @GET
    @Path("/entry")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByAddress(@QueryParam(value = "address") final String address){
	RentEntryBean reb = null;
        try{
            String temp = address;
            String decodedAddr = URLDecoder.decode(temp.replace("+", "%2B"), "UTF-8")
                    .replace("%2B", "+");
            RentBL rentBL = new RentBL();
             reb = rentBL.getByAddress(decodedAddr);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(reb).type(MediaType.APPLICATION_JSON).build();
    }

    /*@GET
    @PATH("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testEntry() {
        String message = "It worked!";
        return Response.status(Response.Status.OK).entity(message).type("text/plain").build();
    }*/

}
