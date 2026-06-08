package br.com.fiap.resources;

import br.com.fiap.bo.EmbarcacaoBO;
import br.com.fiap.entities.Embarcacao;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/embarcacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmbarcacaoResource {

    @Inject
    EmbarcacaoBO bo;

    @GET
    public Response listar() {
        try {
            return Response.ok(bo.listar()).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        try {
            return Response.ok(bo.buscar(id)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(404).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response criar(Embarcacao emb) {
        try {
            bo.cadastrar(emb);
            return Response.status(Response.Status.CREATED).entity(emb).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Embarcacao emb) {
        try {
            bo.atualizar(id, emb);
            return Response.ok(emb).build();
        } catch (IllegalArgumentException e) {
            return Response.status(404).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        try {
            bo.deletar(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}