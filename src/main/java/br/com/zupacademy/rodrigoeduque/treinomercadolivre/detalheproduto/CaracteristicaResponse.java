package br.com.zupacademy.rodrigoeduque.treinomercadolivre.detalheproduto;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.CaracteristicasProdutos;

public class CaracteristicaResponse {
    private String nome;
    private String descricao;

    public CaracteristicaResponse(CaracteristicasProdutos caracteristicasProdutos) {
        this.nome = caracteristicasProdutos.getNome();
        this.descricao = caracteristicasProdutos.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
