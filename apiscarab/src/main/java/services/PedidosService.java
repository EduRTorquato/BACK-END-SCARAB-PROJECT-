/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import enums.Status;
import java.util.List;
import models.Pedidos;
import models.Produto;
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

    public List<Pedidos> findAll() {
        return pedidoRepository.findAll();
    }

    public List<Pedidos> findByUserId(Long id) {

        return pedidoRepository.findByUserId(id);
    }

    public Pedidos createPedido(Pedidos pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedidos alteraStatusProduto(Long id, Status status) {
        Pedidos pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
        return pedido;
    }

}
