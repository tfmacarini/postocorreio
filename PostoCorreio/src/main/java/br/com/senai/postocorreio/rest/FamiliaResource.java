package br.com.senai.postocorreio.rest;

import br.com.senai.postocorreio.dao.FamiliaDAO;
import br.com.senai.postocorreio.model.Familia;
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
@Path("familias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FamiliaResource {
    
    @Inject
    private FamiliaDAO familiaDAO;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Familia insert(Familia familia){
        familiaDAO.insere(familia);
        return familia;
    }
    
    @GET
    @Path("{id}")
    public Response buscar(@PathParam("id") Long id){
        final Familia familia = familiaDAO.buscar(id);
        
        if(familia == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("id inválido").build();
        }
        return Response.ok(familia).build();
    }
    
    
    @GET
    public Response listar() {
        List<Familia> familia = familiaDAO.lista();
        
        GenericEntity entity = new GenericEntity<List<Familia>>(familia){};
        return Response.ok(entity).build();
        }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Familia atualizar(@PathParam("id") Long id, Familia familia){
        if(id != familia.getId()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return familiaDAO.atualizar(familia);
    }
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        familiaDAO.excluir(id);
    }
    
}
