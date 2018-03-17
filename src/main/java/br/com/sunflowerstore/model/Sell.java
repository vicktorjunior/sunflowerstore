package br.com.sunflowerstore.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // usa o autoincremento no código
    private long codigo; // TODO: RNG 003

    private int qtdProd;

    // existe para funcionar a view de new sell (precisa ser removido e a view adaptada)
    @OneToMany(mappedBy = "sell")
    private List<Product> products;

    @OneToMany(mappedBy = "sell")
    private List<ItemSell> itemSell;

    private LocalDateTime timeSell;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public int getQtdProd() {
        return qtdProd;
    }

    public void setQtdProd(int qtdProd) {
        this.qtdProd = qtdProd;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getTimeSell() {
        return timeSell;
    }

    public void setTimeSell(LocalDateTime timeSell) {
        this.timeSell = timeSell;
    }

    public List<ItemSell> getItemSell() {
        return itemSell;
    }

    public void setItemSell(List<ItemSell> itemSell) {
        this.itemSell = itemSell;
    }
}
