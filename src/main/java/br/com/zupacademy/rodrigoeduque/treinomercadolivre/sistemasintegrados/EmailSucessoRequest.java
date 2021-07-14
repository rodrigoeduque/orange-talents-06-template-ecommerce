package br.com.zupacademy.rodrigoeduque.treinomercadolivre.sistemasintegrados;

public class EmailSucessoRequest {

    private Long idCompra;
    private Long idComprador;

    public EmailSucessoRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "Indentif. Compra =" + idCompra +
                ", Identif. Comprador =" + idComprador ;
    }
}
