package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroperguntas;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Usuario usuarioLogado;
    @ManyToOne
    private Produto produto;
    private LocalDateTime dataPergunta = LocalDateTime.now();

    public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario usuarioLogado, @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.usuarioLogado = usuarioLogado;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "titulo='" + titulo + '\'' +
                ", usuarioLogado=" + usuarioLogado +
                ", produto=" + produto +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }
}
