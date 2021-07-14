package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroperguntas;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.Compra;
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

    public void enviaEmailFinalizaçãoCompra(Compra compra) {

        String textoEmail  = "Olá "+ compra.getUsuarioConsumidor().getUsername() + "\n" +
                "\n" +
                "Obrigado pela compra.\n" +
                "\n" +
                "Você comprou\n" +
                "\n" + compra.getQuantidadeCompra() + " - " +
                "Produto : \n" + compra.getProdutoCompra().getNome() +
                "\n" +
                "Chegará em breve : \n" +
                "\t\n" +
                "Resumo da compra\n" +
                "\n" +
                "Você comprou de \n" + compra.getProdutoCompra().getUsuarioProduto().getUsername() +
                "\n" +
                "Pedido Número : " + compra.getId_externo().toString() +
                "\n" +
                "Você pagou pelo : " + compra.getGatewayFormaDePagamento();

        System.out.println(textoEmail);
    }

    public void sucesso(Compra compra) {
    }
}
