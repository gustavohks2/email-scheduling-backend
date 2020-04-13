package br.com.alura.resources;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.entities.AgendamentoEmail;
import br.com.alura.exception.BusinessException;
import br.com.alura.interceptor.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Logger
@Path("/agendamentoemail")
public class AgendamentoEmailResource {

    @Inject
    private AgendamentoEmailBusiness agendamentoEmailBusiness;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAgendamentosEmail() {
        List<AgendamentoEmail> emails = agendamentoEmailBusiness.listarAgendamentoEmail();
        return Response.ok(emails).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvarAgendamentoEmail(AgendamentoEmail agendamentoEmail) throws BusinessException {
        this.agendamentoEmailBusiness.salvarAgendamentoEmail(agendamentoEmail);
        return Response.status(Response.Status.CREATED).build();
    }
}
