package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.anotationpersonalizada.ValorUnico;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank
    @ValorUnico(classe = Categoria.class, atributo = "nome", message = "Categoria já existe")
    private String nome;
    private Long idCategoriaMae;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nome);

        if (idCategoriaMae != null) {
            Optional<Categoria> categoriaMae = categoriaRepository.findById(idCategoriaMae);
            if (categoriaMae.isPresent()) {
                categoria.setMae(categoriaMae.get());
            }
        }

        return categoria;
    }
}
