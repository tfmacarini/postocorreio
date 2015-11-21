package br.com.senai.postocorreio.dao;

import br.com.senai.postocorreio.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UsuarioDAO {
    
    @PersistenceContext(unitName = "postocorreioPU")
    EntityManager em;
    
    public void insere(Usuario usuario) {
        em.persist(usuario);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Usuario.class, id));
    }
    
    public Usuario buscar(Long id) {
        return em.find(Usuario.class, id);
    }
    
    public Usuario atualizar(Usuario usuario) {
        return em.merge(usuario);
    }

    public List<Usuario> lista() {
        TypedQuery<Usuario> q = em.createQuery("SELECT u "
                + "FROM Usuario u ORDER BY u.usuario", Usuario.class);
        return q.getResultList();
    }
    
    public Usuario login(String username, String password) throws SQLException, LoginInvalidoException {
        
        try{
            String jpql = "SELECT u FROM Usuario u WHERE u.usuario = :nome AND u.senha = :senha";
            TypedQuery<Usuario> q = em.createQuery(jpql, Usuario.class);
            q.setParameter("nome", username);
            q.setParameter("senha", password);
            return q.getSingleResult();
        }catch(NoResultException ex){
            throw new LoginInvalidoException();
        }
    }
    
}
