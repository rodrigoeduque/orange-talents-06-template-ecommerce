package br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ValidacaoCaracteristicasIguaisValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        ProdutoRequest request = (ProdutoRequest) o;
        if (request.caracteristicaTemNomeIgual()){
            errors.rejectValue("caracteristicas", null, "Caracteristica já existe");
        }

    }
}
