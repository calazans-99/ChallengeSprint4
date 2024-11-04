package br.com.fiap.resource;

import br.com.fiap.bo.AgendamentoBO;
import br.com.fiap.model.Agendamento;
import br.com.fiap.to.AgendamentoTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/agendamentos")
public class AgendamentoResource {
    private AgendamentoBO agendamentoBO;

    public AgendamentoResource() {
        this.agendamentoBO = new AgendamentoBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarAgendamento(AgendamentoTO agendamento) {
        try {
            agendamentoBO.cadastrarAgendamento(agendamento);
            return Response.status(Response.Status.CREATED).entity(agendamento).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAgendamento(@PathParam("id") int id) {
        try {
            Agendamento agendamento = agendamentoBO.buscarAgendamento(id);
            return Response.ok(agendamento).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarAgendamento(AgendamentoTO agendamento) {
        try {
            agendamentoBO.atualizarAgendamento(agendamento);
            return Response.ok(agendamento).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarAgendamento(@PathParam("id") int id) {
        try {
            agendamentoBO.deletarAgendamento(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Agendamento> listarAgendamentos() {
        return agendamentoBO.listarAgendamentos();
    }
}
