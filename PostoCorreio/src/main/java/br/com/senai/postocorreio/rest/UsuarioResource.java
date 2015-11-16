package br.com.senai.postocorreio.rest;

import br.com.senai.postocorreio.dao.UsuarioDAO;
import br.com.senai.postocorreio.model.Usuario;
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
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @Inject
    private UsuarioDAO usuarioDAO;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario insert(Usuario usuario){
        usuarioDAO.insere(usuario);
        return usuario;
    }
    
    @GET
    @Path("{id}")
    public Response buscar(@PathParam("id") Long id){
        final Usuario usuario = usuarioDAO.buscar(id);
        
        if(usuario == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("id inválido").build();
        }
        return Response.ok(usuario).build();
    }
    
    
    @GET
    public Response listar() {
        List<Usuario> usuario = usuarioDAO.lista();
        
        GenericEntity entity = new GenericEntity<List<Usuario>>(usuario){};
        return Response.ok(entity).build();
        }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario atualizar(@PathParam("id") Long id, Usuario usuario){
        if(id != usuario.getId()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return usuarioDAO.atualizar(usuario);
    }
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        usuarioDAO.excluir(id);
    }
    
}
