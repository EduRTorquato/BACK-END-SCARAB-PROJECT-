/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import java.util.Optional;
import models.Cliente;
import models.ClienteLogin;
import models.Usuario;
import models.UsuarioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.ClienteRepository;
import services.ClienteService;

/**
 *
 * @author eduar
 */
@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ClienteController {

    private final PasswordEncoder encoder;

    public ClienteController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.findAllClient();
    }

    @PostMapping
    public ResponseEntity criarCliente(@RequestBody Cliente cliente) {
        clienteService.cadastroUsuario(cliente);
        return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body("Usuário cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ClienteLogin cliente) {
        if (clienteService.clientLogin(cliente) == null) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body("Usuário não existe na base de dados, ou credenciais estão erradas!");
        }
        return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body("Login realizado!");
    }

    // * Encontra usuário por email
    @GetMapping("/email/{email}")
    public Cliente listaPorEmail(@PathVariable(value = "email") String email) {
        return clienteService.buscaPorEmail(email);
    }

    @GetMapping("/{id}")
    public Optional<Cliente> clienteId(@PathVariable(value = "id") Long id) {
        return clienteService.buscaPorId(id);
    }

}
