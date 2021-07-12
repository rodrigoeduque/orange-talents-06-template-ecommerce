package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroperguntas;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailFake {
    public void paraDono(Usuario usuarioLogado, Produto produto, Pergunta pergunta) {
        System.out.println("----------------INICIO MENSAGEM-----------------------");
        System.out.println("De: " + usuarioLogado.getUsername());
        System.out.println("Para: " + produto.getUsuarioProduto().getUsername());
        System.out.println("Assunto: Dúvidas sobre Produto " + produto.getNome());
        System.out.println("----------------------------------------------------");
        System.out.println(pergunta.getTitulo());
        System.out.println("-----------------FIM MENSAGEM------------------------");

    }
}
