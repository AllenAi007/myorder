package org.ai.order.resource;

import com.google.common.io.Resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

/**
 * Created by hua.ai on 2016/4/20.
 */
@Path("assets")
public class AssetResource {

    @GET
    @Path("css/{filename}")
    @Produces("text/css")
    public byte[] css(@PathParam("filename") String filename) {
        try {
            return Resources.toByteArray(Resources.getResource("assets/css/" + filename));
        } catch (IOException e) {
           throw new WebApplicationException(e);
        }
    }

    @GET
    @Path("image/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] image(@PathParam("filename") String filename) {
        try {
            return Resources.toByteArray(Resources.getResource("assets/image/" + filename));
        } catch (IOException e) {
            throw new WebApplicationException(e);
        }
    }

    @GET
    @Path("fonts/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] fonts(@PathParam("filename") String filename) {
        try {
            return Resources.toByteArray(Resources.getResource("assets/fonts/" + filename));
        } catch (IOException e) {
            throw new WebApplicationException(e);
        }
    }

    @GET
    @Path("js/{filename}")
    @Produces("text/javascript")
    public byte[] js(@PathParam("filename") String filename) {
        try {
            return Resources.toByteArray(Resources.getResource("assets/js/" + filename));
        } catch (IOException e) {
            throw new WebApplicationException(e);
        }
    }

}
