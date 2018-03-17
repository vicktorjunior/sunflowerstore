package br.com.sunflowerstore.service;

import br.com.sunflowerstore.model.ItemSell;
import br.com.sunflowerstore.model.Sell;
import br.com.sunflowerstore.repository.SellRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellService {

    private final SellRepository sellRepository;
    private Sell sell;

    public SellService(SellRepository sellRepository) {
        this.sellRepository = sellRepository;
    }

    public void add(ItemSell itemSell) {
        sell.addProduct((List<ItemSell>) itemSell);
    }
}
