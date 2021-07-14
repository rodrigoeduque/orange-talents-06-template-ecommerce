package br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.request;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.model.StatusRetornoPagSeguro;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.model.Transacao;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.Compra;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.RetornoGatewayPagamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagSeguro status;

    public RetornoPagSeguroRequest(String idTransacao, StatusRetornoPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagSeguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toModelTransacao(Compra compra) {
        return new Transacao(status.normaliza(),idTransacao,compra);
    }
}
