/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;
import java.util.List;
import models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eduar
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "CALL BuscarProdutosPorNome(:name)", nativeQuery = true)
    List<Produto> findByNome(@Param("name") String name);
    
   

    @Query(value = "SELECT * FROM produtos ORDER BY id DESC", nativeQuery = true)
    List<Produto> findAllOrderByIdDesc();

    public Produto findById(int i);

}
