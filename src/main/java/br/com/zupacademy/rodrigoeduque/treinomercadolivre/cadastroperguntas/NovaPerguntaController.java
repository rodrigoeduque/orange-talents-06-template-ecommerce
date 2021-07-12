package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroperguntas;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.ProdutoRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class NovaPerguntaController {

    private UsuarioRepository usuarioRepository;
    private ProdutoRepository produtoRepository;
    private PerguntaRepository perguntaRepository;
    private EnviaEmailFake enviaEmailFake;

    @Autowired
    public NovaPerguntaController(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, EnviaEmailFake enviaEmailFake) {
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.enviaEmailFake = enviaEmailFake;
    }

    @PostMapping("/produtos/{id}/perguntas")
    public ResponseEntity<?> criar(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id){

        Produto produto = produtoRepository.findById(id).get();
        Usuario usuarioLogado = usuarioRepository.findById(4L).get();
        Pergunta pergunta = request.toModel(usuarioLogado,produto);

        perguntaRepository.save(pergunta);

        enviaEmailFake.paraDono(usuarioLogado,produto,pergunta);

        return ResponseEntity.ok().build();
    }
}
