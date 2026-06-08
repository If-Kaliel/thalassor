package br.com.fiap.resources;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.entities.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioBO usuarioBO;

    @GET
    public Response listarTodos() {

        try {

            List<Usuario> lista = usuarioBO.listar();

            return Response.ok(lista).build();

        } catch (Exception e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {

        try {

            Usuario usuario = usuarioBO.buscar(id);

            return Response.ok(usuario).build();

        } catch (IllegalArgumentException e) {

            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    public Response criar(Usuario usuario) {

        try {

            usuarioBO.cadastrar(usuario);

            return Response
                    .status(Response.Status.CREATED)
                    .entity(usuario)
                    .build();

        } catch (IllegalArgumentException e) {

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(
            @PathParam("id") Long id,
            Usuario usuario) {

        try {

            usuarioBO.atualizar(id, usuario);

            return Response.ok(usuario).build();

        } catch (IllegalArgumentException e) {

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {

        try {

            usuarioBO.deletar(id);

            return Response.noContent().build();

        } catch (IllegalArgumentException e) {

            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }
}