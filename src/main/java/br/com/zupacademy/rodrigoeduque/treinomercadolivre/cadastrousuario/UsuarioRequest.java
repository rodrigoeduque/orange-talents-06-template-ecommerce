package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @NotNull
    @Email
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 6, message = "Senha precisa ter no minimo 6 caracteres")
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(login, new SenhaLimpa(senha));
    }

    public UsernamePasswordAuthenticationToken converterDadosAutenticacao() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
