package br.com.zupacademy.rodrigoeduque.treinomercadolivre.sistemasintegrados;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Controller {

    @PostMapping("api/notasfiscais")
    public void criarNota(@RequestBody @Valid NotaFiscalRequest request) throws InterruptedException {
        System.out.println("--------------------    |" + "Gerando NotaFiscal :  " + request + " |    --------------------");
        Thread.sleep(5000);
    }

    @PostMapping("api/rankingvendedores")
    public void enviarRanking(@RequestBody @Valid RankingRequest request) throws InterruptedException {
        System.out.println("--------------------    |" + "RANKING :  " + request + " |    --------------------");
        Thread.sleep(5000);
    }

    @PostMapping("api/emailsucesso")
    public void enviaremailsucesso(@RequestBody @Valid EmailSucessoRequest request) throws InterruptedException {
        System.out.println("EMAIL ENCAMINHADO COM SUCESSO !!!!!!");
        Thread.sleep(5000);
    }


}
