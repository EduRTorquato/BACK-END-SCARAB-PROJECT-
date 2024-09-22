/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;

import models.Produto;
import models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import services.ProdutoService;

/**
 *
 * @author eduar
 */
@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.buscaProdutos();
    }

    // * Encontra usuário por nome
    @GetMapping("/produto/{nome}")
    public List<Produto> listaPorNome(@PathVariable(value = "nome") String nome) {
        return produtoService.buscaProdutoPorNome(nome);
    }

    //Cadastro de produto
    @PostMapping()
    public ResponseEntity<Object> criarNovoProdutoComImagens(
            @RequestParam("nome") String nome,
            @RequestParam("preco") Double preco,
            @RequestParam("avaliacao") Double avaliacao,
            @RequestParam("quantidadeEstoque") int quantidadeEstoque,
            @RequestParam("descricao") String descricao,
            @RequestParam("imagens") MultipartFile[] files) {

        // Criar um novo produto com os dados do ProdutoDTO
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setAvaliacao(avaliacao);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setDescricao(descricao);
        // Salvar o produto no banco de dados primeiro
        Produto produtoSalvo = produtoService.salvaProduto(produto);

        // Chama o serviço para salvar as imagens e associar ao produto criado
        produtoService.salvarImagensParaProduto(produtoSalvo, files);

        return ResponseEntity.ok("Produto criado e imagens associadas com sucesso!");
    }

    @PutMapping("/desativar")
    public ResponseEntity<Object> desativarProduto(@RequestBody Produto produto) {
        produtoService.desativaProduto(produto);
        return ResponseEntity.ok("Produto criado e imagens associadas com sucesso!");
    }

    @PutMapping("/ativar")
    public ResponseEntity<Object> ativarProduto(@RequestBody Produto produto) {
        produtoService.ativaProduto(produto);
        return ResponseEntity.ok("Produto criado e imagens associadas com sucesso!");
    }

}
