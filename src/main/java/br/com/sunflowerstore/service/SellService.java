package br.com.sunflowerstore.service;

import br.com.sunflowerstore.model.ItemSell;
import br.com.sunflowerstore.model.Sell;
import br.com.sunflowerstore.repository.ItemSellRepository;
import br.com.sunflowerstore.repository.SellRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellService {

    private final SellRepository sellRepository;
    private final ItemSellRepository itemSellRepository;


    public SellService(SellRepository sellRepository, ItemSellRepository itemSellRepository) {
        this.sellRepository = sellRepository;
        this.itemSellRepository = itemSellRepository;
    }

    public Sell getOne(Long id){
        return sellRepository.findOne(id);
    }

    public List<Sell> getAll(){
        return sellRepository.findAll();
    }

    public Sell save(Sell sell){
        return sellRepository.save(sell);
    }

    public Sell add(Sell sell, ItemSell itemSell) {
        sell.getItems().add(itemSell);
        itemSell.setSell(sell);
        itemSellRepository.save(itemSell);

        System.out.println(sell.getItems().toString());

        return sell;
    }

}
