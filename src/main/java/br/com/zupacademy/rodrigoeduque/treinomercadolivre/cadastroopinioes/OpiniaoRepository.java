package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroopinioes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao,Long> {

}
