package br.com.fiap.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {

    @GET
    public Response home() {
        return Response.ok(Map.of(
                "projeto", "Thalassor",
                "status", "API em execução",
                "endpoints", Map.of(
                        "usuarios", "/usuarios",
                        "regioes", "/regioes",
                        "focos", "/focos",
                        "ordens", "/ordens",
                        "swagger", "/swagger",
                        "devUi", "/q/dev-ui"
                )
        )).build();
    }
}