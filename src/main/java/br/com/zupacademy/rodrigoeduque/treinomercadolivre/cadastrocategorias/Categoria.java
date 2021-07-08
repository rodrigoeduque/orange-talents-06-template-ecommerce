package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;

    @Deprecated
    public Categoria() {
    }

    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(@NotBlank String nome) {

        this.nome = nome;
    }

    public void setMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

}
