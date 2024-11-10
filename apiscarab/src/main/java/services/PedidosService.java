/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import models.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PedidoRepository;

/**
 *
 * @author eduar
 */
@Service
public class PedidosService {
    
    @Autowired
    public PedidoRepository pedidoRepository;
    
    public List<Pedidos> findAll(){
        return pedidoRepository.findAll();
    }
    
}
