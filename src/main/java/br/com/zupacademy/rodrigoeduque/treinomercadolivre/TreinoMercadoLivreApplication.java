package br.com.zupacademy.rodrigoeduque.treinomercadolivre;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.seguranca.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication //(exclude = {SecurityConfiguration.class})
public class TreinoMercadoLivreApplication {

	public static void main(String[] args) {

		SpringApplication.run(TreinoMercadoLivreApplication.class, args);
	}

}
