package br.com.sunflowerstore.service;

import br.com.sunflowerstore.model.ItemSell;
import br.com.sunflowerstore.model.Sell;
import br.com.sunflowerstore.repository.SellRepository;
import org.springframework.stereotype.Service;

@Service
public class SellService {

    private final SellRepository sellRepository;


    public SellService(SellRepository sellRepository) {
        this.sellRepository = sellRepository;
    }

    public void add(ItemSell itemSell, Sell sell) {
        //String nomeProduct = itemSell.getProduct().getNome();

        sell.addProduct(itemSell);
        System.out.println(sell.getItemSell().toString());
    }

}
