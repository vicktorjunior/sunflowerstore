package br.com.sunflowerstore.repository;

import br.com.sunflowerstore.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell,Long> {

    public List<Sell> findAllByTime(LocalDateTime localDateTime);
}
