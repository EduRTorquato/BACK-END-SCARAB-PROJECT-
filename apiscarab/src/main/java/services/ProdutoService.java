/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
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
        return produtoRepository.findAll();
    }
    
    
}
