package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Valid
    @ManyToOne
    private Produto produto;
    @URL
    @NotBlank
    private String link;



    public ImagemProduto(@NotNull @Valid Produto produto, @URL String link) {
        this.produto = produto;
        this.link = link;
    }

    @Deprecated
    public ImagemProduto() {
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                ", link='" + link + '\'' +
                '}';
    }
}
