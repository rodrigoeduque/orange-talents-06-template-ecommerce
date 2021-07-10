package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
