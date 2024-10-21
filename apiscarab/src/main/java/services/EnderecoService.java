/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import models.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EnderecoRepository;

/**
 *
 * @author eduar
 */
@Service
public class EnderecoService {
    
    @Autowired EnderecoRepository enderecoRepository;
    
    public List<Endereco> buscaEnderecos(){
        return enderecoRepository.findAll();
    }
    
    
    
}
