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
	private long code;

	@NotBlank(message = "O name do supplier é obrigatório")
	private String name;

	@OneToMany(mappedBy = "supplier")
	private List<Product> products;

	public Supplier() {
		super();
	}

	public Supplier(long code, String name, List<Product> products) {
		super();
		this.code = code;
		this.name = name;
		this.products = products;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Supplier that = (Supplier) o;

		return code == that.code;
	}

	@Override
	public int hashCode() {
		return (int) (code ^ (code >>> 32));
	}
}
