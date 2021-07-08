package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class NovaCategoriaController {

    private CategoriaRepository repository;

    @Autowired
    public NovaCategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/categorias")
    public ResponseEntity<?> criar(@RequestBody @Valid CategoriaRequest categoriaRequest) {

        Categoria categoriaModel = categoriaRequest.toModel(repository);

        repository.save(categoriaModel);

        return ResponseEntity.ok().build();
    }


}
