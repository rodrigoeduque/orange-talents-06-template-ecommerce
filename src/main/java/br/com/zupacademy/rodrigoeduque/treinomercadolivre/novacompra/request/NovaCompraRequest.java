package br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.request;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.GatewayFormaDePagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {
    @Positive
    private Integer quantidade;

    @NotNull
    private Long idProduto;

    @NotNull
    private GatewayFormaDePagamento gatewayFormaDePagamento;

    public NovaCompraRequest(Integer quantidade, Long idProduto, GatewayFormaDePagamento gatewayFormaDePagamento) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gatewayFormaDePagamento = gatewayFormaDePagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayFormaDePagamento getGatewayFormaDePagamento() {
        return gatewayFormaDePagamento;
    }
}
