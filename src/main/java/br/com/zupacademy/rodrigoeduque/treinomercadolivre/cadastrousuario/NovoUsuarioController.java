package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.validator.ValidaEmailUnicoUsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class NovoUsuarioController {

    private UsuarioRepository repository;
    private ValidaEmailUnicoUsuarioValidator validaEmailUnicoUsuarioValidator;

    @Autowired
    public NovoUsuarioController(UsuarioRepository repository, ValidaEmailUnicoUsuarioValidator validaEmailUnicoUsuarioValidator) {
        this.repository = repository;
        this.validaEmailUnicoUsuarioValidator = validaEmailUnicoUsuarioValidator;
    }

    @InitBinder
    public void Init(WebDataBinder binder){
        binder.addValidators(validaEmailUnicoUsuarioValidator);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> criar(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = request.toModel();
        repository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
