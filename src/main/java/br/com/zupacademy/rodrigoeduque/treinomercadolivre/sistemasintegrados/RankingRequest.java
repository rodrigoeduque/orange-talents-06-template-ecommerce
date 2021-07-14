package br.com.zupacademy.rodrigoeduque.treinomercadolivre.sistemasintegrados;

public class RankingRequest {
    private Long idCompra;
    private Long idComprador;

    public RankingRequest(Long idCompra, Long idComprador) {
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
