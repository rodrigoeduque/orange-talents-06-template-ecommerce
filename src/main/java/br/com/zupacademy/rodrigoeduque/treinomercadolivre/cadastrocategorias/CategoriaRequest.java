package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrocategorias;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.anotationpersonalizada.existeid.ExisteId;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.anotationpersonalizada.valorunico.ValorUnico;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank
    @ValorUnico(classe = Categoria.class, atributo = "nome", message = "Categoria já existe")
    private String nome;

   @ExisteId(classe = Categoria.class, atributo = "id")
    @Positive
    private Long idCategoriaMae;

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
