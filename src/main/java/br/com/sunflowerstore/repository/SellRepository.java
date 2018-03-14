package br.com.sunflowerstore.repository;

import br.com.sunflowerstore.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository extends JpaRepository<Sell,Long> {

}
