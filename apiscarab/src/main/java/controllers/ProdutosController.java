/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DTOs.ProdutoDTO;
import java.util.List;
import java.util.stream.Collectors;
import models.Imagem;

import models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping
    public ResponseEntity<?> salvarProduto(@RequestBody ProdutoDTO produtoDTO) {
        try {
            // Converter DTO para Entidade
            Produto produto = new Produto();
            produto.setNome(produtoDTO.getNome());
            produto.setPreco(produtoDTO.getPreco());
            produto.setDescricao(produtoDTO.getDescricao());
            produto.setAtivo(produtoDTO.isAtivo());
            produto.setAvaliacao(produtoDTO.getAvaliacao());
            produto.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());

            // Converter Imagens DTO para Entidade
            List<Imagem> imagens = produtoDTO.getImagens().stream()
                    .map(imagemDTO -> new Imagem(imagemDTO.getCaminho(), imagemDTO.isPrincipal()))
                    .collect(Collectors.toList());

            produto.setImagens(imagens);

            // Salvar Produto com Imagens
            Produto produtoSalvo = produtoService.salvaProduto(produto);

            return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar produto.");
        }
    }

    // Método para desativar o produto (ativo = 0)
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Produto> desativarProduto(@PathVariable Long id) {
        Produto produtoAtualizado = produtoService.desativaProduto(id);
        return ResponseEntity.ok(produtoAtualizado);
    }

   // Método para ativar o produto (ativo = 1)
    @PutMapping("/{id}/ativar")
    public ResponseEntity<Produto> ativarProduto(@PathVariable Long id) {
        Produto produtoAtualizado = produtoService.ativaProduto(id);
        return ResponseEntity.ok(produtoAtualizado);
    }
}
