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

    public void add(Sell sell, ItemSell itemSell) {
        //if(itemSell.getSell()!= null) {
            itemSell.setSell(sell);
        //} else {
            //itemSell.setSell(itemSell.getSell().addProduct());
        //}
        //String nomeProduct = itemSell.getProduct().getName();

        sell.addProduct(itemSell);
        //System.out.println(sell.getItems().toString());
    }

}
