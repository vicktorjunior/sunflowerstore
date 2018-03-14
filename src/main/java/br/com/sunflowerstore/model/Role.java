package br.com.sunflowerstore.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by rodrigo on 3/18/17.
 */
@Entity
//@Table(name = "roles") // todo: descomentar para usar postgresql
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String role;
	@ManyToMany
	@Column(name = "accounts")
	private Set<User> accounts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<User> accounts) {
		this.accounts = accounts;
	}
}
