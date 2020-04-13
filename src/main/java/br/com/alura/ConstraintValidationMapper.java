package br.com.alura;


import br.com.alura.dto.MensagemErroDto;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class ConstraintValidationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        MensagemErroDto mensagemErroDto = MensagemErroDto.build(
            e.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage).collect(Collectors.toList())
        );
        return Response.status(Response.Status.BAD_REQUEST).entity(mensagemErroDto).build();
    }

}
