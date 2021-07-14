package br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.request.RetornoPagSeguroRequest;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.request.RetornoPayPalRequest;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.Compra;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.RetornoGatewayPagamento;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.repository.CompraRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.sistemasintegrados.EmailSucesso;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.sistemasintegrados.NotaFiscal;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.sistemasintegrados.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class FinalizacaoCompraController {

    private CompraRepository compraRepository;
    private NotaFiscal notaFiscal;
    private Ranking ranking;
    private EmailSucesso email;

    @Autowired
    public FinalizacaoCompraController(CompraRepository compraRepository, NotaFiscal notaFiscal, Ranking ranking, EmailSucesso email) {
        this.compraRepository = compraRepository;
        this.notaFiscal = notaFiscal;
        this.ranking = ranking;
        this.email = email;
    }

    @PostMapping("api/compras/retorno-pagseguro/{id}")
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagSeguroRequest request) {
        return processaPagamento(idCompra, request);
    }

    @PostMapping("api/compras/retorno-paypal/{id}")
    public String processamentoPayPal(@PathVariable("id") Long idCompra, @Valid RetornoPayPalRequest request) {
        return processaPagamento(idCompra, request);
    }

    private String processaPagamento(Long idCompra, RetornoGatewayPagamento request) {
        Compra compra = compraRepository.findById(idCompra).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID COMPRA INVÁLIDO OU NÃO EXISTE : " + idCompra));
        compra.adicionaTransacao(request);
        compraRepository.saveAndFlush(compra);

        if (compra.processadaComSucesso()) {
            notaFiscal.processa(compra);
            ranking.processa(compra);
            email.sucesso(compra);

        }

        return request.toString();
    }
}
