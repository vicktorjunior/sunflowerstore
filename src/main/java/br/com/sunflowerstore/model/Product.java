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
import java.util.List;

/**
 * Created by VictorJr on 23/03/2017.
 */
@Entity
@Table(name = "produtos") // determina o nome da tabela no banco
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // usa o autoincremento no código
	private long codigo; // TODO: RNG 003

	@NotBlank(message = "Apelido é obrigatório")
	private String apelido; // TODO: RNG 003

	@NotBlank(message = "Nome é obrigatório")
	private String nome; // TODO: RNG 003

	@NotBlank(message = "O tamanho da descrição deve estar entre 1 e 1500")
	@Size(min = 1, max = 1500)
	private String descricao; // TODO: RNG 003

	//@NotNull(message = "Data de compra é obrigatório")
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
	//@Temporal(TemporalType.DATE)
	//@Column(name = "data_compra")
	private LocalDate dataCompra; // TODO: RNG 003

	@Enumerated(EnumType.STRING) // salva como o nome da category
	@NotNull(message = "Category é obrigatória")
	private Category category; // TODO: RNG 003

	@ManyToOne
	@JoinColumn(name = "codigo_fornecedor")
	@NotNull(message = "Supplier é obrigatório")
	private Supplier supplier; // TODO: RNG 003

	@ManyToOne
	@JoinColumn(name = "sell_product")
	//@NotNull(message = "Supplier é obrigatório")
	private Sell sell; // TODO: RNG 003

	@OneToMany(mappedBy = "product")
	private List<ItemSell> itemSell;


	@NotNull(message = "Origin é obrigatório")
	@Enumerated
	private Origin origin; // TODO: RNG 003

	@Column(name = "preco_compra") // determina o nome da coluna no banco
	@NotNull(message = "Preço de Compra é obrigatório")
	private BigDecimal precoCompra; // TODO: RNG 003

	@Column(name = "preco_venda")
	@NotNull(message = "Preço de Venda é obrigatório")
	private BigDecimal precoVenda; // TODO: RNG 003

	@NotNull(message = "Percentual é obrigatório")
	private BigDecimal percentual; // TODO: RNG 003

	@Column(name = "quantidade_estoque")
	@NotNull(message = "Quantidade em Estoque é obrigatório")
	private Integer qtd; // TODO: RNG 003

	private byte[] foto;

	@Column(name = "content_type")
	private String contentType;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(BigDecimal precoCompra) {
		this.precoCompra = precoCompra;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

	public List<ItemSell> getItemSell() {
		return itemSell;
	}

	public void setItemSell(List<ItemSell> itemSell) {
		this.itemSell = itemSell;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Product product = (Product) o;

		return codigo == product.codigo;
	}

	@Override
	public int hashCode() {
		return (int) (codigo ^ (codigo >>> 32));
	}
}
