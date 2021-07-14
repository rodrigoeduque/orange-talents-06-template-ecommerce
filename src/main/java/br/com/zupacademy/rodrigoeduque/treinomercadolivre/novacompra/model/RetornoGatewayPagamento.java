package br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.model.Transacao;

public interface RetornoGatewayPagamento {
    Transacao toModelTransacao(Compra compra);
}
