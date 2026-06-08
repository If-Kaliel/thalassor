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
                "descricao", "API para monitoramento de focos de poluição marinha com apoio de imagens orbitais.",
                "documentacao", Map.of(
                        "swagger", "/swagger",
                        "openapi", "/q/openapi"
                ),
                "endpoints", Map.of(
                        "usuarios", "/usuarios",
                        "regioes", "/regioes",
                        "embarcacoes", "/embarcacoes",
                        "focos", "/focos",
                        "ordens", "/ordens"
                )
        )).build();
    }
}