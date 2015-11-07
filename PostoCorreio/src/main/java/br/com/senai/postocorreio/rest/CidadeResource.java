package br.com.senai.postocorreio.rest;

import br.com.senai.postocorreio.dao.CidadeDAO;
import br.com.senai.postocorreio.model.Cidade;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("cidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource {
    
    @Inject
    private CidadeDAO cidadeDAO;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Cidade insert(Cidade cidade){
        cidadeDAO.insere(cidade);
        return cidade;
    }
    
    @GET
    @Path("{id}")
    public Response buscar(@PathParam("id") Long id){
        final Cidade cidade = cidadeDAO.buscar(id);
        
        if(cidade == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("id inválido").build();
        }
        return Response.ok(cidade).build();
    }
    
}
