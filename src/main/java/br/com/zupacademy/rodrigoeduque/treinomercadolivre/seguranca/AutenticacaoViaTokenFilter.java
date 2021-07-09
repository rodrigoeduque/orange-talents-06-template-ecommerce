package br.com.zupacademy.rodrigoeduque.treinomercadolivre.seguranca;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenServices tokenServices;

    private UsuarioRepository usuarioRepository;

    @Autowired
    public AutenticacaoViaTokenFilter(TokenServices tokenServices, UsuarioRepository usuarioRepository) {
        this.tokenServices = tokenServices;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);

        boolean tokenEhValido = tokenServices.isTokenValido(token);
       // System.out.println("O TOKEN É VALIDO ? : " + tokenEhValido);

        if (tokenEhValido){
            autenticarCliente(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenServices.getIdUsuario(token);

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

      //  System.out.println("USUARIO SENDO CHAMADO NO FILTER EH : " + usuario);

        UsernamePasswordAuthenticationToken dadosAutenticacao = new UsernamePasswordAuthenticationToken(usuario,null);
        SecurityContextHolder.getContext().setAuthentication(dadosAutenticacao);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
      //  System.out.println("TOKEN CARREGADO NO FILTER EH : " + token);
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){

            return null;
        }

        return token.substring(7, token.length());
    }
}
