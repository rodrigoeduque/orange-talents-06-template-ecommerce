package br.com.zupacademy.rodrigoeduque.treinomercadolivre.sistemasintegrados;

public class NotaFiscalRequest {

    private Long idCompra;
    private Long idComprador;

    public NotaFiscalRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "Sucesso " +
                "Indentif. Compra =" + idCompra +
                ", Identif. Comprador =" + idComprador ;
    }
}
