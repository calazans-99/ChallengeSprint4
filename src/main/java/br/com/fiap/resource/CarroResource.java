package br.com.fiap.resource;

import br.com.fiap.Main;
import br.com.fiap.bo.CarroBO;
import br.com.fiap.model.Carro;
import br.com.fiap.to.CarroTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/carros")
public class CarroResource {
    private CarroBO carroBO;

    public CarroResource() {
        // Obtém a conexão do Main e inicializa o CarroBO
        this.carroBO = new CarroBO(Main.getConnection());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCarros() {
        try {
            List<Carro> carros = carroBO.listarCarros();
            return Response.ok(carros).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar carros: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarCarro(CarroTO carro) {
        try {
            carroBO.cadastrarCarro(carro);
            return Response.status(Response.Status.CREATED).entity(carro).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro de validação: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar carro: " + e.getMessage())
                    .build();
        }
    }
}
