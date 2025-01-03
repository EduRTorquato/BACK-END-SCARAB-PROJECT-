/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import java.util.Optional;
import models.Endereco;
import models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.EnderecoService;

/**
 *
 * @author eduar
 */
@RestController
@Controller
@RequestMapping("/endereco")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> findAllAddress() {
        return enderecoService.buscaEnderecos();
    }

    @PostMapping
    public ResponseEntity criarEndereco(@RequestBody Endereco endereco) {
        enderecoService.salvaEndereco(endereco);
        return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body("Endereço cadastrado com sucesso!");
    }
    
    @GetMapping("cliente/{id}")
    public List<Endereco> enderecoPorUserId(@PathVariable(value = "id") Long id) {
        return enderecoService.buscaEnderecoPorUserId(id);
    }
    
    @GetMapping("/{id}")
     public Optional<Endereco> enderecoPorId(@PathVariable(value = "id") Long id) {
        return enderecoService.buscaEnderecoPorId(id);
     }
}
