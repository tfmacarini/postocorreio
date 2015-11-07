package br.com.senai.postocorreio.dao;

import br.com.senai.postocorreio.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
        TypedQuery<Usuario> q = em.createQuery("SELECT u.usuario "
                + "FROM Usuario u ORDER BY u.usuario", Usuario.class);
        return q.getResultList();
    }
    
}
