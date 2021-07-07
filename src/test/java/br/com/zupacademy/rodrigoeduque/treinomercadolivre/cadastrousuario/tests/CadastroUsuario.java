package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.tests;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.SenhaLimpa;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@SpringBootTest()
public class CadastroUsuario {

    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveTrazerUmUsuarioExistente(){
        Optional<Usuario> retorno = repository.findByLogin("rodrigoduque@email.com");
        Assert.assertEquals(true,retorno.isPresent());
    }
}
