package br.com.alura.mdb;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.entities.AgendamentoEmail;
import br.com.alura.interceptor.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Logger
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/EmailQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailMDB implements MessageListener {

    @Inject
    private AgendamentoEmailBusiness agendamentoEmailBusiness;

    @Override
    public void onMessage(Message message) {
        try {
            AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
            agendamentoEmailBusiness.enviarEmail(agendamentoEmail);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
