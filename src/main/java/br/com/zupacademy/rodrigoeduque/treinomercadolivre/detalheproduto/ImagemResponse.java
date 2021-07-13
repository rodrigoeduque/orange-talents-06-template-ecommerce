package br.com.zupacademy.rodrigoeduque.treinomercadolivre.detalheproduto;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.ImagemProduto;

public class ImagemResponse {
    private String link;

    public ImagemResponse(ImagemProduto imagem) {
        this.link = imagem.getLink();
    }

    public String getLink() {
        return link;
    }
}
