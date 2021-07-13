package br.com.zupacademy.rodrigoeduque.treinomercadolivre.detalheproduto;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroopinioes.Opiniao;

public class AvaliacaoResponse {
    private String titulo;
    private String descricao;
    private Integer nota;

    public AvaliacaoResponse(Opiniao avaliacao) {
        this.titulo = avaliacao.getTitulo();
        this.descricao = avaliacao.getDescricao();
        this.nota = avaliacao.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }
}
