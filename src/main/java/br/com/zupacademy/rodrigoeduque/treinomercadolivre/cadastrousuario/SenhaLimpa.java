package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {
    private String senha;

    public String getSenha() {
        return senha;
    }

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }


    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
