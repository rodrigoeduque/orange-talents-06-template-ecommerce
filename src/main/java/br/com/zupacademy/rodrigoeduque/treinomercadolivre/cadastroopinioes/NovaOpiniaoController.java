package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroopinioes;


import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.ProdutoRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public class NovaOpiniaoController {

    private ProdutoRepository produtoRepository;
    private UsuarioRepository usuarioRepository;
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    public NovaOpiniaoController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, OpiniaoRepository opiniaoRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.opiniaoRepository = opiniaoRepository;
    }

    @PostMapping("produtos/{id}/opinioes")
   public String criar(@RequestBody @Valid NovaOpiniaoRequest request, @PathVariable("id") Long id){
        Usuario cliente = usuarioRepository.findById(3L).get();
        Produto produto = produtoRepository.findById(id).get();

       Opiniao opiniao = request.toModel(produto, cliente);
       opiniaoRepository.save(opiniao);

       return opiniao.toString();
   }
}
