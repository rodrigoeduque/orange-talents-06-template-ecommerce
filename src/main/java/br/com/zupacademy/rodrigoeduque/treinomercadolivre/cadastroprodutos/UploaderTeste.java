package br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderTeste {
    public List<String> envia(List<MultipartFile> img) {



        List<String> listaLinks = img.stream().map(
                imagem ->
                        "http://linkhospedagem.io/" + imagem.getOriginalFilename().substring(0,imagem.getOriginalFilename().length() -4) +
                                "-" + UUID.randomUUID().toString().concat("imagem_hospedada")
        +  imagem.getOriginalFilename().substring(imagem.getOriginalFilename().length() -4)).collect(Collectors.toList());

        return listaLinks;
    }
}
