package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias.Categoria;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroopinioes.Opiniao;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroperguntas.Pergunta;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private String descricao;
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario usuarioProduto;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicasProdutos> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagens = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private Set<Opiniao> opinioes = new HashSet<>();


    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @Positive Integer quantidade,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria, Usuario usuarioProduto,
                   @Size(min = 3) @Valid Collection<CaracteristicasProdutosRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuarioProduto = usuarioProduto;
        Set<CaracteristicasProdutos> novasCaracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuarioProduto() {
        return usuarioProduto;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicasProdutos> getCaracteristicas() {
        return caracteristicas;
    }

    public List<ImagemProduto> getImagens() {
        return imagens;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", instanteCadastro=" + instanteCadastro +
                ", categoria=" + categoria +
                ", usuarioProduto=" + usuarioProduto +
                ", caracteristicas=" + caracteristicas +
                ", imagens=" + imagens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;

        Produto produto = (Produto) o;

        return nome != null ? nome.equals(produto.nome) : produto.nome == null;
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }

    public void insereImagens(List<String> linksImagem) {
        List<ImagemProduto> imagens = linksImagem.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toList());

        this.imagens.addAll(imagens);
    }

    public boolean pertenceUsuarioLogado(Usuario usuarioProduto) {

        return this.usuarioProduto.equals(usuarioProduto);
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }
}
