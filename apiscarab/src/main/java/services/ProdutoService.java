/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import java.util.List;
import models.Imagem;
import models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProdutoRepository;

/**
 *
 * @author eduar
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscaProdutos() {
        return produtoRepository.findAllOrderByIdDesc();
    }

    public List<Produto> buscaProdutoPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    //SALVA PRODUTO E IMAGENS
    public Produto salvaProduto(Produto produto) {

        // Validação: Garantir que apenas uma imagem é principal
        List<Imagem> imagens = produto.getImagens();

        long countPrincipais = imagens.stream().filter(Imagem::isPrincipal).count();
        if (countPrincipais > 1) {
            throw new IllegalArgumentException("Apenas uma imagem pode ser marcada como principal.");
        }

        // Associar cada imagem ao produto
        if (imagens != null) {
            imagens.forEach(imagem -> imagem.setProduto(produto));
            produto.setImagens(imagens);
        }
        
        produto.setAtivo(true);
        return produtoRepository.save(produto);
    }

    //Ativa produto no sistema
    public Produto ativaProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
        produto.setAtivo(true);
        produtoRepository.save(produto);

        return produto;
    }

    //Desativa produto no sistema
    public Produto desativaProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
        produto.setAtivo(false);
        produtoRepository.save(produto);
        return produto;
    }
}
