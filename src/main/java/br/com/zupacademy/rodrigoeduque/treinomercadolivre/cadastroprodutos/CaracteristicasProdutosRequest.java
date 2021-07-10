package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicasProdutosRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicasProdutosRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "CaracteristicasProdutos{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public CaracteristicasProdutos toModel(@NotNull @Valid Produto produto) {
        return new CaracteristicasProdutos(nome,descricao,produto);
    }
}
