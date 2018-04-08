package br.com.sunflowerstore.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ItemSell {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer qtd;

    private BigDecimal discount;

    @OneToOne
    private Product product;

    @ManyToOne
    private Sell sell;

    public ItemSell() {

    }

    public ItemSell(Integer qtd, BigDecimal discount, Product product, Sell sell) {
        this.qtd = qtd;
        this.discount = discount;
        this.product = product;
        this.sell = sell;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Transient
    public BigDecimal getUnitValue() {

        return discount.multiply(product.getSellingPrice());
    }

    @Override
    public String toString() {
        return "ItemSell{" +
                "id=" + id +
                ", qtd=" + qtd +
                ", product=" + product +
                '}';
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }
}
