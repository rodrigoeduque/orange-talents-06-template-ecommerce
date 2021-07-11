package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias.CategoriaRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.ValidacaoCaracteristicasIguaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProdutosController {

    private CategoriaRepository categoriaRepository;
    private UsuarioRepository usuarioRepository;
    private ProdutoRepository produtoRepository;
    private UploaderTeste uploaderTeste;

    @Autowired
    public ProdutosController(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository, UploaderTeste uploaderTeste) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.uploaderTeste = uploaderTeste;
    }

    @InitBinder("produtoRequest")
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

    @PostMapping("/produtos/{id}/img")
    public ResponseEntity<?> adicionaImagem(@PathVariable("id") Long id, @Valid ImagemProdutoRequest imagemProdutoRequest){
        Usuario usuarioProduto = usuarioRepository.findById(1L).get();
        Produto produto = produtoRepository.findById(id).get();

        if (!produto.pertenceUsuarioLogado(usuarioProduto)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }


        List<String> linksImagem = uploaderTeste.envia(imagemProdutoRequest.getImg());
        System.out.println(linksImagem);
        produto.insereImagens(linksImagem);

        produtoRepository.saveAndFlush(produto);

        return ResponseEntity.ok().build();
    }
}
