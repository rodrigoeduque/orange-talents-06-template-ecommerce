package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
