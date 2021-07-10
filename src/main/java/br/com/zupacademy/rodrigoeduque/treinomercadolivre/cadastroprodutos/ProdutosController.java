package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias.CategoriaRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.ValidacaoCaracteristicasIguaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProdutosController {

    private CategoriaRepository categoriaRepository;
    private UsuarioRepository usuarioRepository;
    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutosController(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ValidacaoCaracteristicasIguaisValidator());
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> criar(@RequestBody @Valid ProdutoRequest produtoRequest){
        Usuario usuarioProduto = usuarioRepository.findById(2L).get();
        Produto produto = produtoRequest.toModel(categoriaRepository,usuarioProduto);
        produtoRepository.save(produto);


        return ResponseEntity.ok().build();
    }
}
