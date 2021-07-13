package br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

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


    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Valid Produto produtoCompra, @Positive Integer quantidadeCompra, @NotNull @Valid Usuario usuarioConsumidor, GatewayFormaDePagamento gatewayFormaDePagamento) {
        this.produtoCompra = produtoCompra;
        this.quantidadeCompra = quantidadeCompra;
        this.usuarioConsumidor = usuarioConsumidor;
        this.gatewayFormaDePagamento = gatewayFormaDePagamento;
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
}
