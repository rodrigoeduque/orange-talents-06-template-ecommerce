package br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.model;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.model.StatusTransacao;

public enum StatusRetornoPagSeguro {
SUCESSO,ERRO;

    public StatusTransacao normaliza() {
        if (this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }
}
