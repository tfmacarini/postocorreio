package br.com.senai.postocorreio.dao;

import br.com.senai.postocorreio.model.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PessoaDAO {
    
    @PersistenceContext(unitName = "postocorreioPU")
    EntityManager em;
    
    public void insere(Pessoa pessoa) {
        em.persist(pessoa);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Pessoa.class, id));
    }
    
    public Pessoa buscar(Long id) {
        return em.find(Pessoa.class, id);
    }
    
    public Pessoa atualizar(Pessoa pessoa) {
        return em.merge(pessoa);
    }

    public List<Pessoa> lista() {
        TypedQuery<Pessoa> q = em.createQuery("SELECT p "
                + "FROM Pessoa p ORDER BY p.nome", Pessoa.class);
        return q.getResultList();
    }
    
}
