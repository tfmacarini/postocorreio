package br.com.senai.postocorreio.dao;

import br.com.senai.postocorreio.model.Familia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class FamiliaDAO {
    
    @PersistenceContext(unitName = "postocorreioPU")
    private EntityManager em;
    
    public void insere(Familia familia) {
        em.persist(familia);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Familia.class, id));
    }
    
    public Familia buscar(Long id) {
        return em.find(Familia.class, id);
    }
    
    public Familia atualizar(Familia familia) {
        return em.merge(familia);
    }

    public List<Familia> lista() {
        TypedQuery<Familia> q = em.createQuery("SELECT f "
                + "FROM Familia f ORDER BY f.descricao", Familia.class);
        return q.getResultList();
    }
    
    
}
