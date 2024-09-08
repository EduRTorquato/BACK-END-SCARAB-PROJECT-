/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Usuario;
import models.UsuarioLogin;
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
import services.UsuarioService;

/**
 *
 * @author eduar
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.buscaUsuarios();
    }

    // * Encontra usuário por nome
    @GetMapping("/usuario/{nome}")
    public Usuario listaPorNome(@PathVariable(value = "nome") String nome) {
        return usuarioService.buscaPorNome(nome);
    }

    // * Encontra usuário por email
    @GetMapping("/email/{email}")
    public Usuario listaPorEmail(@PathVariable(value = "email") String email) {
        return usuarioService.buscaPorEmail(email);
    }

    // * Cadastro de usuário
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Usuario usuario) {
        if (usuarioService.userExistsByEmail(usuario.getEmail())
                || usuarioService.userExistsByCpf(usuario.getCpf())
                || usuarioService.userExistsByNome(usuario.getNomeUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe na base de dados!");
        }
        usuarioService.cadastroUsuario(usuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário cadastrado com sucesso!");
    }

    // * Login de usuário
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UsuarioLogin usuario) {
        
        if (usuarioService.userLogin(usuario) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não existe na base de dados, ou credenciais estão erradas!");
        }else if(usuarioService.isUserActive(usuario.getEmail())){
            return ResponseEntity.status(HttpStatus.LOCKED).body("Usuário está inativo na base de dados!");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login realizado!");
    }

    // * Ativa usuário no sistema
    @PutMapping("/ativar")
    public ResponseEntity<Object> activeUser(@RequestBody Usuario usuario) {
        usuarioService.activeUser(usuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário ativo na base de dados!");
    }

    // *Desativa usuário no sistema
    @PutMapping("/desativar")
    public ResponseEntity<Object> deactivateUser(@RequestBody Usuario usuario) {
        usuarioService.deactivateUser(usuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário desativado na base dados!");
    }

}
