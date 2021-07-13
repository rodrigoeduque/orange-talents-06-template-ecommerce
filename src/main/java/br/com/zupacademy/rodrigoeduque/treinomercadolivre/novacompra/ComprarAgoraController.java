package br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra;

import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.Produto;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastroprodutos.ProdutoRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.Compra;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.model.GatewayFormaDePagamento;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.repository.CompraRepository;
import br.com.zupacademy.rodrigoeduque.treinomercadolivre.novacompra.request.NovaCompraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ComprarAgoraController {

    private ProdutoRepository produtoRepository;
    private UsuarioRepository usuarioRepository;
    private CompraRepository compraRepository;

    @Autowired
    public ComprarAgoraController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, CompraRepository compraRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.compraRepository = compraRepository;
    }

    @PostMapping("/compras")
    public ResponseEntity<?> novaCompra(@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Usuario usuarioConsumidor = usuarioRepository.findByLogin("usuarioconsumidor@email.com").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não permitido"));
        Produto produtoCompra = produtoRepository.findById(request.getIdProduto()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto informado não Existe " + request.getIdProduto()));
        Integer quantidadeCompra = request.getQuantidade();
        boolean baixaNoEstoqueEfetuada = produtoCompra.baixaEstoque(request.getQuantidade());
        GatewayFormaDePagamento gatewayFormaDePagamento = request.getGatewayFormaDePagamento();

        if (baixaNoEstoqueEfetuada) {
            Compra compraFinalizada = new Compra(produtoCompra, quantidadeCompra, usuarioConsumidor, request.getGatewayFormaDePagamento());
            compraRepository.save(compraFinalizada);

            if (gatewayFormaDePagamento.equals(GatewayFormaDePagamento.pagseguro)) {
                String urlPagSeguro = uriComponentsBuilder.path("/compras/pagseguro.com?returnId={id_externo}&redirectUrl=urlRetornoAppPosPagamentoPAGSEGURO").buildAndExpand(compraFinalizada.getId_externo()).toString();
                return ResponseEntity.status(HttpStatus.FOUND).body(urlPagSeguro);


            } else if (gatewayFormaDePagamento.equals(GatewayFormaDePagamento.paypal)) {
                String urlPayPal = uriComponentsBuilder.path("/compras/paypal.com?buyerId={id_externo}&redirectUrl=urlRetornoAppPosPagamentoPAYPAL").buildAndExpand(compraFinalizada.getId_externo()).toString();
                return ResponseEntity.status(HttpStatus.FOUND).body(urlPayPal);

            }
        }
        BindException exceptionEstoque = new BindException(request, "NovaCompraRequest");
        exceptionEstoque.reject(null, "Quantidade a ser comprada maior que o estoque disponível. Estoque atual = " + produtoCompra.getQuantidade());
        return ResponseEntity.badRequest().body(exceptionEstoque.getAllErrors());
    }
}
