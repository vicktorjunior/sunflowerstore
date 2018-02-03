package br.com.sunflowerstore.repository;

import br.com.sunflowerstore.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by VictorJr on 08/04/2017.
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    public Optional<Supplier> findByNomeIgnoreCase(String nome);

}
