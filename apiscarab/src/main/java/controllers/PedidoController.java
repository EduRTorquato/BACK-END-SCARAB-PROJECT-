/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.Status;
import java.util.List;
import models.Endereco;
import models.Pedidos;
import models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.PedidosService;

/**
 *
 * @author eduar
 */
@RestController
@Controller
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PedidoController {

    @Autowired
    private PedidosService pedidoService;

    @GetMapping
    public List<Pedidos> findAll() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public List<Pedidos> pedidoPorUserId(@PathVariable(value = "id") Long id) {
        return pedidoService.findByUserId(id);
    }

    @PostMapping
    public ResponseEntity salvaPedido(@RequestBody Pedidos pedido) {
        pedido.setStatus(Status.PREPARACAO);
        pedidoService.createPedido(pedido);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Pedido Salvo com sucesso!");
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<Pedidos> ativarProduto(@PathVariable Long id, @PathVariable Status status) {
        Pedidos pedidoAtualizado = pedidoService.alteraStatusProduto(id, status);
        return ResponseEntity.ok(pedidoAtualizado);
    }

}
