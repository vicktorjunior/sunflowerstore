package br.com.sunflowerstore.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ItemSell {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer qtd;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "sell_id")
    private List<Sell> sell;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<Sell> getSell() {
        return sell;
    }

    public void setSell(List<Sell> sell) {
        this.sell = sell;
    }
}
