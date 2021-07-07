package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByLogin(@Email String login);

}
