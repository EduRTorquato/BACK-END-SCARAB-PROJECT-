/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> findAllAddress() {
        return enderecoService.buscaEnderecos();
    }

    @PostMapping
    public ResponseEntity criarCliente(@RequestBody Endereco endereco) {
        enderecoService.salvaEndereco(endereco);
        return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body("Usuário cadastrado com sucesso!");
    }

}
