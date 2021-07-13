package br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.repository;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
