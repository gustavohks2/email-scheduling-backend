package br.com.alura.exception;

import javax.ejb.ApplicationException;
import java.util.ArrayList;
import java.util.List;

@ApplicationException(rollback = true)
public class BusinessException extends Exception {

    private List<String> mensagens;

    public BusinessException() {
        super();
        this.mensagens = new ArrayList<>();
    }

    public BusinessException(String mensagem) {
        super(mensagem);
        this.mensagens = new ArrayList<>();
        this.mensagens.add(mensagem);
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    public void addMensagem(String mensagens) {
        this.mensagens.add(mensagens);
    }
}
