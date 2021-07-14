package br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.encerramentoCompra.model.Transacao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID id_externo = UUID.randomUUID();
    @Positive
    private Integer quantidadeCompra;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produtoCompra;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuarioConsumidor;
    @Enumerated
    private StatusCompra statusCompra = StatusCompra.iniciada;
    @Enumerated
    @NotNull
    private GatewayFormaDePagamento gatewayFormaDePagamento;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();


    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Valid Produto produtoCompra, @Positive Integer quantidadeCompra, @NotNull @Valid Usuario usuarioConsumidor, GatewayFormaDePagamento gatewayFormaDePagamento) {
        this.produtoCompra = produtoCompra;
        this.quantidadeCompra = quantidadeCompra;
        this.usuarioConsumidor = usuarioConsumidor;
        this.gatewayFormaDePagamento = gatewayFormaDePagamento;
    }

    public Long getId() {
        return id;
    }

    public UUID getId_externo() {
        return id_externo;
    }

    public Integer getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public Produto getProdutoCompra() {
        return produtoCompra;
    }

    public Usuario getUsuarioConsumidor() {
        return usuarioConsumidor;
    }

    public GatewayFormaDePagamento getGatewayFormaDePagamento() {
        return gatewayFormaDePagamento;
    }

    public Set<Transacao> getTransacoes() {
        return transacoes;
    }

    public void adicionaTransacao(RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toModelTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao),"Já existe uma transação processada : " + novaTransacao.toString());
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluida com sucesso");

        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }


    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", id_externo=" + id_externo +
                ", quantidadeCompra=" + quantidadeCompra +
                ", statusCompra=" + statusCompra +
                ", gatewayFormaDePagamento=" + gatewayFormaDePagamento +
                '}';
    }
}
