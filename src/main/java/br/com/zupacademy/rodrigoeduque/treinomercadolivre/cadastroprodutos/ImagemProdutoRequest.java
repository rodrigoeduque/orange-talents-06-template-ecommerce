package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemProdutoRequest {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> img = new ArrayList<>();

    public ImagemProdutoRequest(List<MultipartFile> img) {
        this.img = img;
    }

    public List<MultipartFile> getImg() {
        return img;
    }
}
