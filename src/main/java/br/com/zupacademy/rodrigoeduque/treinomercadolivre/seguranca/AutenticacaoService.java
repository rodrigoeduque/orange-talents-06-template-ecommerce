package br.com.zupacademy.rodrigoeduque.treinomercadolivre.seguranca;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

        if (usuario.isPresent()){
            return usuario.get();
        }
        throw new UsernameNotFoundException("Usuário ou Senha Inválidos");
    }
}
