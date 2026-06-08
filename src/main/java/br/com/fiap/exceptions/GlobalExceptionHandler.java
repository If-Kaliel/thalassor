package br.com.fiap.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        exception.printStackTrace();

        if (exception instanceof IllegalArgumentException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Map.of("erro", exception.getMessage()))
                    .build();
        }

        if (exception instanceof SQLIntegrityConstraintViolationException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Map.of(
                            "erro", "Não foi possível concluir a operação porque existem registros relacionados no banco."
                    ))
                    .build();
        }

        if (exception instanceof WebApplicationException webException) {
            int status = webException.getResponse().getStatus();

            String mensagem = switch (status) {
                case 400 -> "Requisição inválida.";
                case 401 -> "Não autorizado.";
                case 403 -> "Acesso negado.";
                case 404 -> "Recurso não encontrado.";
                case 405 -> "Método HTTP não permitido para este recurso.";
                case 415 -> "Tipo de mídia não suportado.";
                default -> "Erro na requisição.";
            };

            return Response.status(status)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Map.of(
                            "erro", mensagem,
                            "status", status,
                            "tipo", exception.getClass().getName()
                    ))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(Map.of(
                        "erro", "Ocorreu um erro interno. Tente novamente mais tarde.",
                        "tipo", exception.getClass().getName(),
                        "mensagem", exception.getMessage() == null ? "Sem mensagem detalhada." : exception.getMessage()
                ))
                .build();
    }
}