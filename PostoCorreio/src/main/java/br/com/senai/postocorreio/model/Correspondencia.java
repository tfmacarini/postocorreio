package br.com.senai.postocorreio.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//@Entity
//@Table(name="CORRESPONDENCIA")
public class Correspondencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private Pessoa pessoa;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_chegada", nullable = true)
    private Date dt_chegada;
    
    @Column(name = "tipo", length = 5)
    private String tipo;
    
    @Column(name = "entregue", length = 1)
    private Boolean entregue;

    public Correspondencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDt_chegada() {
        return dt_chegada;
    }

    public void setDt_chegada(Date dt_chegada) {
        this.dt_chegada = dt_chegada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(Boolean entregue) {
        this.entregue = entregue;
    }
    
    
    
}
