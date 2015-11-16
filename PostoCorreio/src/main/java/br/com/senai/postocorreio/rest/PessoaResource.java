package br.com.senai.postocorreio.rest;

import br.com.senai.postocorreio.dao.PessoaDAO;
import br.com.senai.postocorreio.model.Pessoa;
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
@Path("pessoas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaResource {
    
    @Inject
    private PessoaDAO pessoaDAO;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Pessoa insert(Pessoa pessoa){
        pessoaDAO.insere(pessoa);
        return pessoa;
    }
    
    @GET
    @Path("{id}")
    public Response buscar(@PathParam("id") Long id){
        final Pessoa pessoa = pessoaDAO.buscar(id);
        
        if(pessoa == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("id inválido").build();
        }
        return Response.ok(pessoa).build();
    }
    
    
    @GET
    public Response listar() {
        List<Pessoa> pessoa = pessoaDAO.lista();
        
        GenericEntity entity = new GenericEntity<List<Pessoa>>(pessoa){};
        return Response.ok(entity).build();
        }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Pessoa atualizar(@PathParam("id") Long id, Pessoa pessoa){
        if(id != pessoa.getId()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return pessoaDAO.atualizar(pessoa);
    }
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        pessoaDAO.excluir(id);
    }
    
}
