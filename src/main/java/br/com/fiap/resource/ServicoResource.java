package br.com.fiap.resource;

import br.com.fiap.bo.ServicoBO;
import br.com.fiap.to.ServicoTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/servicos")
public class ServicoResource {
    private ServicoBO servicoBO;

    public ServicoResource() {
        this.servicoBO = new ServicoBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarServico(ServicoTO servico) {
        try {
            servicoBO.cadastrarServico(servico);
            return Response.status(Response.Status.CREATED).entity(servico).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarServico(@PathParam("id") int id) {
        try {
            ServicoTO servico = servicoBO.buscarServico(id);
            return Response.ok(servico).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarServico(ServicoTO servico) {
        try {
            servicoBO.atualizarServico(servico);
            return Response.ok(servico).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarServico(@PathParam("id") int id) {
        try {
            servicoBO.deletarServico(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServicoTO> listarServicos() throws SQLException {
        return servicoBO.listarServicos();
    }
}
