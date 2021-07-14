package br.com.zupacademy.rodrigoeduque.treinomercadolivre.sistemasintegrados;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal {
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra",compra.getId(),"idComprador",compra.getUsuarioConsumidor().getId());
        restTemplate.postForEntity("http://localhost:8080/api/notasfiscais", request, String.class);

    }
}
