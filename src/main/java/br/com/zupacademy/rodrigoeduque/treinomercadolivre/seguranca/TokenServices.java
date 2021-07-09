package br.com.zupacademy.rodrigoeduque.treinomercadolivre.seguranca;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServices {

    @Value("${treinomercadolivre.jwt.expiration}")
    private String expiration;

    @Value("${treinomercadolivre.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authenticate) {

        Usuario usuarioLogado = (Usuario) authenticate.getPrincipal();
        Date dataAtual = new Date();
        Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API MercadoLivre")
                .setSubject(usuarioLogado.getId().toString())
                .setIssuedAt(dataAtual)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {

        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
