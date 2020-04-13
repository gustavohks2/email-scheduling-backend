package br.com.alura.dao;

import br.com.alura.entities.AgendamentoEmail;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AgendamentoEmailDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AgendamentoEmail> listarAgendamentoEmail() {
        return this.entityManager
                .createQuery("SELECT a FROM AgendamentoEmail a", AgendamentoEmail.class)
                .getResultList();
    }

    public void salvarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
        this.entityManager.persist(agendamentoEmail);
    }

    public List<AgendamentoEmail> listarAgendamentosEmailPorEmail(String email) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a FROM AgendamentoEmail a ");
        sql.append("WHERE a.email = :email and a.enviado = false");

        Query query = this.entityManager.createQuery(sql.toString(), AgendamentoEmail.class);
        query.setParameter("email", email);

        return query.getResultList();
    }

    public List<AgendamentoEmail> listarAgendamentosEmailNaoEnviados() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a FROM AgendamentoEmail a ");
        sql.append("WHERE a.enviado = false");

        Query query = this.entityManager.createQuery(sql.toString(), AgendamentoEmail.class);

        return query.getResultList();
    }

    public void atualizarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
        this.entityManager.merge(agendamentoEmail);
    }
}
