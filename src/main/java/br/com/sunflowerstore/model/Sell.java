package br.com.sunflowerstore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sell {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // usa o autoincremento no código
    private long code; // TODO: RNG 003

    @OneToMany(mappedBy = "sell")
    private List<ItemSell> items = new ArrayList<ItemSell>();

    private LocalDateTime time;

    public Sell() {
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<ItemSell> getItems() {
        return items;
    }

    public void setItems(List<ItemSell> items) {
        this.items = items;
    }

    public void addProduct(ItemSell item) {
        items.add(item);
    }

    @Transient
    public BigDecimal getTotalSell(Integer id) {
        BigDecimal totalSell = new BigDecimal(0);
        for (ItemSell item : items) {
            int qtdInt = item.getQtd();
            BigDecimal qtdBD = new BigDecimal(qtdInt);
            totalSell.add(item.getUnitValue().multiply(qtdBD));
        }
        return totalSell;
    }
}
