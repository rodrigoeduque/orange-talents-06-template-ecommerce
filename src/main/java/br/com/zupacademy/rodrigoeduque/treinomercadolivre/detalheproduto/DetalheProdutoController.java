package br.com.zupacademy.rodrigoeduque.treinomercadolivre.detalheproduto;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DetalheProdutoController {

    private ProdutoRepository produtoRepository;

    @Autowired
    public DetalheProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping(value = "/produtos/{id}")
    public ResponseEntity<DetalheProdutoResponse> exibeDetalhesProdutos(@PathVariable("id") Long id) {

        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {

            return ResponseEntity.ok().body(new DetalheProdutoResponse(produto.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
