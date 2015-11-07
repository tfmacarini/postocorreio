package br.com.senai.postocorreio.dao;

import br.com.senai.postocorreio.model.Correspondencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CorrespondenciaDAO {
    
    @PersistenceContext(unitName = "postocorreioPU")
    private EntityManager em;
    
    public void insere(Correspondencia correspondencia) {
        em.persist(correspondencia);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Correspondencia.class, id));
    }
    
    public Correspondencia buscar(Long id) {
        return em.find(Correspondencia.class, id);
    }
    
    public Correspondencia atualizar(Correspondencia correspondencia) {
        return em.merge(correspondencia);
    }

    public List<Correspondencia> lista() {
        TypedQuery<Correspondencia> q = em.createQuery("SELECT c "
                + "FROM Correspondencia c ORDER BY c.dt_chegada", Correspondencia.class);
        return q.getResultList();
    }
    
}
