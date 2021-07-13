package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroopinioes;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;
    @NotNull
    @Range(min = 1, max = 5)
    private Integer nota;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario cliente;


    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@NotBlank String titulo, @NotBlank @Length(max = 500) String descricao, @NotNull @Range(min = 1, max = 5) Integer nota, @NotNull @Valid Produto produto, @NotNull @Valid Usuario cliente) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.produto = produto;
        this.cliente = cliente;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public Produto getProduto() {
        return produto;
    }

    public void SomaNota() {
        this.nota += nota;
    }


}
