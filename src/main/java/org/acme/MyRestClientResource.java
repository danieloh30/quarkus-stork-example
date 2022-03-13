package org.acme;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class MyRestClientResource {
    
    @RestClient MyRestClient myRestClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String invoke() {
        return myRestClient.get();
    }

}
