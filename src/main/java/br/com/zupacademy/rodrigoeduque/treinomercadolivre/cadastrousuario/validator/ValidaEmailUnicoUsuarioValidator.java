package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.validator;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Configuration
public class ValidaEmailUnicoUsuarioValidator implements Validator {

    private UsuarioRepository repository;

    @Autowired
    public ValidaEmailUnicoUsuarioValidator(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsuarioRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        UsuarioRequest request = (UsuarioRequest) o;

        Optional<Usuario> possivelEmailUsuario = repository.findByLogin(request.getLogin());

        if (possivelEmailUsuario.isPresent()) {
            errors.rejectValue("login",null,  "Já existe um login com este E-mail cadastrado no Sistema.");
        }
    }
}
