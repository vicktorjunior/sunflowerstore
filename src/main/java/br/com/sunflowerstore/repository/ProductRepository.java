package br.com.sunflowerstore.repository;

import br.com.sunflowerstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by VictorJr on 05/04/2017.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> { // jpa repository que já tem métodos prontos

}
