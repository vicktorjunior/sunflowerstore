package br.com.sunflowerstore.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by VictorJr on 04/04/2017.
 */
@Entity
@Table(name = "fornecedores")
public class Supplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6263502418153760574L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;

	@NotBlank(message = "O nome do supplier é obrigatório")
	private String nome;

	@OneToMany(mappedBy = "supplier")
	private List<Product> products;

	public Supplier() {
		super();
	}

	public Supplier(long codigo, String nome, List<Product> products) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.products = products;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Supplier that = (Supplier) o;

		return codigo == that.codigo;
	}

	@Override
	public int hashCode() {
		return (int) (codigo ^ (codigo >>> 32));
	}
}
