package br.com.senai.postocorreio.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.senai.postocorreio.model.Cidade;

@Stateless
public class CidadeDAO {
    
    @PersistenceContext(unitName = "postocorreioPU")
    private EntityManager em;

    public void insere(Cidade cidade) {
        em.persist(cidade);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Cidade.class, id));
    }
    
    public Cidade buscar(Long id) {
        return em.find(Cidade.class, id);
    }
    
    public Cidade atualizar(Cidade cidade) {
        return em.merge(cidade);
    }

    public List<Cidade> lista() {
        TypedQuery<Cidade> q = em.createQuery("SELECT c "
                + "FROM Cidade c ORDER BY c.nome", Cidade.class);
        return q.getResultList();
    }
    
    
}
