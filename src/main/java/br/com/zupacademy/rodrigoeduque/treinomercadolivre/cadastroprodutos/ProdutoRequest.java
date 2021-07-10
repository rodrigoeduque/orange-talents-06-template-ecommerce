package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias.Categoria;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias.CategoriaRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.anotationpersonalizada.existeid.ExisteId;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.anotationpersonalizada.valorunico.ValorUnico;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class ProdutoRequest {

    @NotBlank
    @ValorUnico(classe = Produto.class,atributo = "nome", message = "Já existe um produto cadastrado com esse nome")
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ExisteId(classe = Categoria.class, atributo = "id")
    private Long idCategoria;

    @Size(min = 3)
    @Valid
    //@ValorUnico(classe = CaracteristicasProdutos.class, atributo = "nome", message = "Já existe uma categoria cadastrada com esse nome")
    private List<CaracteristicasProdutosRequest> caracteristicas = new ArrayList<>();

    public ProdutoRequest(String nome,
                          BigDecimal valor,
                          Integer quantidade,
                          String descricao,
                          Long idCategoria,
                          List<CaracteristicasProdutosRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public List<CaracteristicasProdutosRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(CategoriaRepository categoriaRepository,
                           Usuario usuarioProduto) {

        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);


        return new Produto(nome, valor, quantidade, descricao, categoria.get(), usuarioProduto, caracteristicas);
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    public boolean caracteristicaTemNomeIgual() {

        HashSet<String> nomesIguais = new HashSet<>();

        for (CaracteristicasProdutosRequest caracteristicasProdutosRequest : caracteristicas){
            if (!nomesIguais.add(caracteristicasProdutosRequest.getNome())){
                return true;
            }
        }

        return false;
    }
}
