package br.com.zupacademy.rodrigoeduque.treinomercadolivre.detalheproduto;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Opinioes;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalheProdutoResponse {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Set<CaracteristicaResponse> caracteristicas;
    private List<ImagemResponse> imagens;
    private List<PergutaResponse> perguntas;
    private Set<Map<String, String>> opinioes;
    private double mediaNotas;
    private int total;


    public DetalheProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas().stream().map(caracteristica -> new CaracteristicaResponse(caracteristica)).collect(Collectors.toSet());
        this.imagens = produto.getImagens().stream().map(imagem -> new ImagemResponse(imagem)).collect(Collectors.toList());
        this.perguntas = produto.getPerguntas().stream().map(pergunta -> new PergutaResponse(pergunta)).collect(Collectors.toList());
        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        });
        this.mediaNotas = opinioes.media();
        this.total = opinioes.total();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<ImagemResponse> getImagens() {
        return imagens;
    }

    public List<PergutaResponse> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotal() {
        return total;
    }
}
