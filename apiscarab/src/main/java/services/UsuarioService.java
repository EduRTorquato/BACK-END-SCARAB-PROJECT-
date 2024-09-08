/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import enums.Grupo;
import java.util.List;
import models.Usuario;
import models.UsuarioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UsuarioRepository;

/**
 *
 * @author eduar
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder encoder;

    public UsuarioService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    //Lista todos os usuários  
    public List<Usuario> buscaUsuarios() {
        return usuarioRepository.findAll();
    }

    //Busca usuários por nome
    public Usuario buscaPorNome(String nome) {
        return usuarioRepository.findByNomeUsuario(nome);
    }

    //RETORNA UM USUÁRIO PELO EMAIL
    public Usuario buscaPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    //Cadastro de usuários
    public Usuario cadastroUsuario(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        //Define um valor padrão para os atributos Grupo e Ativo
        usuario.setActive(1);
        usuario.setGrupo(Grupo.ESTOQUISTA);
        return usuarioRepository.save(usuario);
    }

    //Login de usuários
    public UsuarioLogin userLogin(UsuarioLogin usuario) {
        Usuario usuarioEncontrado = usuarioRepository.findByEmail(usuario.getEmail());
        boolean validate = encoder.matches(usuario.getSenha(), usuarioEncontrado.getSenha());
        if (usuarioEncontrado == null || !validate) {
            return null;
        }
        return usuario;
    }

    //Verificação por email
    public boolean userExistsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    //Verificação por nome
    public boolean userExistsByNome(String nome) {
        return usuarioRepository.existsByNomeUsuario(nome);
    }

    //Verificação por CPF
    public boolean userExistsByCpf(String cpf) {
        return usuarioRepository.existsByCpf(cpf);
    }

    public boolean isUserActive(String email) {
        return usuarioRepository.findByEmail(email).getActive() == 0;
    }

    //Ativa usuário no sistema
    public Usuario activeUser(Usuario usuario) {
        Usuario ativarUsuario = buscaPorEmail(usuario.getEmail());
        ativarUsuario.setActive(1);
        usuarioRepository.save(ativarUsuario);
        return ativarUsuario;
    }

    //Desativa usuário no sistema
    public Usuario deactivateUser(Usuario usuario) {
        Usuario ativarUsuario = buscaPorEmail(usuario.getEmail());
        ativarUsuario.setActive(0);
        usuarioRepository.save(ativarUsuario);
        return ativarUsuario;
    }

}
