package br.com.zupacademy.rodrigoeduque.treinomercadolivre.config.validacaohandler.request;

public class ErrosRequest {

    private String campo;
    private String mensagemErro;

    public ErrosRequest(String campo, String mensagemErro) {
        this.campo = campo;
        this.mensagemErro = mensagemErro;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}
