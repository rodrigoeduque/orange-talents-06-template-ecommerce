package br.com.zupacademy.rodrigoeduque.treinomercadolivre.detalheproduto;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroperguntas.Pergunta;

public class PergutaResponse {
    private String titulo;

    public PergutaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
