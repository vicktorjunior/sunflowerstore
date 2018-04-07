package br.com.sunflowerstore.model;

import br.com.sunflowerstore.enums.Category;
import br.com.sunflowerstore.enums.Origin;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by VictorJr on 23/03/2017.
 */
@Entity
@Table(name = "product") // determina o name da tabela no banco
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // usa o autoincremento no código
	private Long code; // TODO: RNG 003

	@NotBlank(message = "Apelido é obrigatório")
	private String nickname; // TODO: RNG 003

	@NotBlank(message = "Nome é obrigatório")
	private String name; // TODO: RNG 003

	@NotBlank(message = "O tamanho da descrição deve estar entre 1 e 1500")
	@Size(min = 1, max = 1500)
	private String description; // TODO: RNG 003

	//@NotNull(message = "Data de compra é obrigatório")
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
	//@Temporal(TemporalType.DATE)
	//@Column(name = "data_compra")
	private LocalDate buyingDate; // TODO: RNG 003

	@Enumerated(EnumType.STRING) // salva como o name da category
	@NotNull(message = "Category é obrigatória")
	private Category category; // TODO: RNG 003

	@ManyToOne
	@JoinColumn(name = "codigo_fornecedor")
	@NotNull(message = "Supplier é obrigatório")
	private Supplier supplier; // TODO: RNG 003

	@NotNull(message = "Origin é obrigatório")
	@Enumerated
	private Origin origin; // TODO: RNG 003

	@Column(name = "buying_price") // determina o name da coluna no banco
	@NotNull(message = "Preço de Compra é obrigatório")
	private BigDecimal buyingPrice; // TODO: RNG 003

	@Column(name = "selling_price")
	@NotNull(message = "Preço de Venda é obrigatório")
	private BigDecimal sellingPrice; // TODO: RNG 003

	@NotNull(message = "Percentual é obrigatório")
	private BigDecimal percentage; // TODO: RNG 003

	@Column(name = "quantidade_estoque")
	@NotNull(message = "Quantidade em Estoque é obrigatório")
	private Integer qtd; // TODO: RNG 003

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(LocalDate buyingDate) {
		this.buyingDate = buyingDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
}
