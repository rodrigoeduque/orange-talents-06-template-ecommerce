package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroopinioes;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NovaOpiniaoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    @NotNull
    @Range(min = 1,max = 5)
    private Integer nota;

    public NovaOpiniaoRequest(String titulo, String descricao, Integer nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "NovaOpiniaoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario cliente) {
        return new Opiniao(titulo,descricao,nota,produto,cliente);
    }
}
