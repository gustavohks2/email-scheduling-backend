package br.com.alura.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_agendamento_email")
public class AgendamentoEmail implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotBlank(message = "{agendamentoEmail.email.vazio}")
    @Email(message = "{agendamentoEmail.email.invalido}")
    private String email;

    @Column
    @NotBlank(message = "{agendamentoEmail.assunto.vazio}")
    private String assunto;

    @Column
    @NotBlank(message = "{agendamentoEmail.mensagem.vazio}")
    private String mensagem;

    @Column
    private Boolean enviado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgendamentoEmail that = (AgendamentoEmail) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
