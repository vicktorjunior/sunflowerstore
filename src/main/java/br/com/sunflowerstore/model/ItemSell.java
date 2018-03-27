package br.com.sunflowerstore.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ItemSell {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer qtd;

    private BigDecimal desconto;

    @OneToOne
    private Product product;

    @ManyToOne
    private Sell sell;

    public ItemSell() {
        product = new Product();
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

        return desconto.multiply(product.getPrecoVenda());
    }

    @Override
    public String toString() {
        return "ItemSell{" +
                "id=" + id +
                ", qtd=" + qtd +
                ", product=" + product +
                '}';
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }
}
