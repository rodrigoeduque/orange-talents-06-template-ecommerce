package br.com.zupacademy.rodrigoeduque.treinomercadolivre.seguranca;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private TokenServices tokenService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, TokenServices tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> autenticar (@RequestBody @Valid UsuarioRequest request){
        UsernamePasswordAuthenticationToken dadosAutenticacao = request.converterDadosAutenticacao();
            try {
                Authentication authenticate = authenticationManager.authenticate(dadosAutenticacao);
                String token = tokenService.gerarToken(authenticate);
                return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
            }
            catch (AuthenticationException exception){
                return ResponseEntity.badRequest().body(exception.getMessage());
            }
    }
}
