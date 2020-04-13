package br.com.alura.timer;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.entities.AgendamentoEmail;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.List;

@Singleton
public class AgendamentoEmailTimer {

    @Inject
    private AgendamentoEmailBusiness agendamentoEmailBusiness;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/EmailQueue")
    private Queue queue;

    @Schedule(hour = "*", minute = "*")
    public void enviarEmailsAgendados() {
        List<AgendamentoEmail> agendamentoEmails = this.agendamentoEmailBusiness.listarAgendamentosEmailNaoEnviados();
        agendamentoEmails.stream()
                .forEach(ae -> {
                    context.createProducer().send(queue, ae);
                    agendamentoEmailBusiness.marcarEnviadas(ae);
                });
    }
}

