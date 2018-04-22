package br.com.sunflowerstore.service;

import br.com.sunflowerstore.model.ItemSell;
import br.com.sunflowerstore.model.Sell;
import br.com.sunflowerstore.repository.ItemSellRepository;
import br.com.sunflowerstore.repository.SellRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        BigDecimal disc =
                (itemSell.getDiscount().multiply(itemSell.getProduct().getSellingPrice())).
                        divide(new BigDecimal(100),2).multiply(new BigDecimal(itemSell.getQtd()));

        BigDecimal tot =  itemSell.getProduct().getSellingPrice().multiply(new BigDecimal(itemSell.getQtd())).subtract(disc);

        itemSell.setTotal(tot);
        //System.out.println("total do item no service = "+ itemSell.getTotal());
        sell.getItems().add(itemSell);
        sell.setTotalSell(itemSell.getTotal());
        //System.out.println("total do SELL no service = " + sell.getTotalSell());
        itemSell.setSell(sell);
        itemSellRepository.save(itemSell);

        return sell;
    }

    public void deleteItem(Sell sell, Long id) {
        sell.getItems().remove(id);
        itemSellRepository.delete(id);
    }

}
