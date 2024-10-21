/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import models.Cliente;
import models.ClienteLogin;
import models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import repository.ClienteRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author eduar
 */
@Service
public class ClienteService {

    private final PasswordEncoder encoder;

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    //Cadastro de usuários
    public Cliente cadastroUsuario(Cliente cliente) {
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        return clienteRepository.save(cliente);
    }

    //Login Usuário
    public ClienteLogin clientLogin(ClienteLogin cliente) {
        Cliente usuarioEncontrado = clienteRepository.findByEmail(cliente.getEmail());
        boolean validate = encoder.matches(cliente.getSenha(), usuarioEncontrado.getSenha());
        if (usuarioEncontrado == null || !validate) {
            return null;
        }
        return cliente;
    }

    public List<Cliente> findAllClient() {
        return clienteRepository.findAll();
    }

    //RETORNA UM USUÁRIO PELO EMAIL
    public Cliente buscaPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

}
