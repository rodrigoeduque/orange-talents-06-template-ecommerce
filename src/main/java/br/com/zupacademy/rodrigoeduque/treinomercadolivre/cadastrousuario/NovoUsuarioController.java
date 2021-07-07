package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class NovoUsuarioController {

    private UsuarioRepository repository;

    @Autowired
    public NovoUsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> criar(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = request.toModel();
        repository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
