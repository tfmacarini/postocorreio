package br.com.senai.postocorreio.rest;

import br.com.senai.postocorreio.dao.CorrespondenciaDAO;
import br.com.senai.postocorreio.model.Correspondencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("correspondencias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CorrespondenciaResource {
    
    @Inject
    private CorrespondenciaDAO correspondenciaDAO;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Correspondencia insert(Correspondencia correspondencia){
        correspondenciaDAO.insere(correspondencia);
        return correspondencia;
    }
    
    @GET
    @Path("{id}")
    public Response buscar(@PathParam("id") Long id){
        final Correspondencia correspondencia = correspondenciaDAO.buscar(id);
        
        if(correspondencia == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("id inválido").build();
        }
        return Response.ok(correspondencia).build();
    }
    
    
    @GET
    public Response listar() {
        List<Correspondencia> correspondencia = correspondenciaDAO.lista();
        
        GenericEntity entity = new GenericEntity<List<Correspondencia>>(correspondencia){};
        return Response.ok(entity).build();
        }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Correspondencia atualizar(@PathParam("id") Long id, Correspondencia correspondencia){
        if(id != correspondencia.getId()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return correspondenciaDAO.atualizar(correspondencia);
    }
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        correspondenciaDAO.excluir(id);
    }
    
}
