/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import models.Endereco;
import models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author eduar
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    @Query(value = "CALL GET_ADDRESS_USER(:id)", nativeQuery = true)
    List<Endereco> findAddressByUser(@Param("id") Long id);
}
