package br.com.zupacademy.rodrigoeduque.treinomercadolivre.config.validacaohandler;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.config.validacaohandler.request.ErrosRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorValidationHandler {

    private MessageSource messageSource;

    @Autowired
    public ErrorValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrosRequest> handle(MethodArgumentNotValidException exception) {

        List<ErrosRequest> errosRequests = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrosRequest erro = new ErrosRequest(e.getField(), mensagem);
            errosRequests.add(erro);
        });

        return errosRequests;
    }
}
