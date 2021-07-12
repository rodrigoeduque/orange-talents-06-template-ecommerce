package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroperguntas;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    @Deprecated
    public NovaPerguntaRequest() {
    }

    public NovaPerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "NovaPerguntaRequest{" +
                "titulo='" + titulo + '\'' +
                '}';
    }

    public Pergunta toModel(Usuario usuarioLogado, Produto produto) {
        return new Pergunta(titulo,usuarioLogado,produto);
    }
}
