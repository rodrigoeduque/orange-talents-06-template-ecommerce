package br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.request;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.model.StatusTransacao;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.model.Transacao;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.Compra;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.RetornoGatewayPagamento;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPayPalRequest implements RetornoGatewayPagamento {
    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;

    public RetornoPayPalRequest(int status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toModelTransacao(Compra compra) {
        StatusTransacao statusCalculado = this.status==0?StatusTransacao.erro:StatusTransacao.sucesso;

        return new Transacao(statusCalculado,idTransacao,compra);
    }
}
