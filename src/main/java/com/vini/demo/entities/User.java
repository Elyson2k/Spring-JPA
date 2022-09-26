package com.vini.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Dizendo que é uma entidade do meu bando de dados.
@Table(name = "users")
public class User implements Serializable{ // Serializable serve para que o objeto trafegue na rede, possa
	// ser gravado em arquivods, e por ai vai.
	private static final long serialVersionUID = 1L;
	
	@Id // Dizendo qual campo vai ser a PK(Primary Key)
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Incrementando automaticamente o ID.
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@JsonIgnore // Comando com o intuito de não causar loop, pois vai um pedido vai chamar um cliente, um cliete vai chamar um pedido, e fica nesse loot.
	@OneToMany(mappedBy = "client") // Relação 1 para muitos, tem que passar um mappedBy = "Obj que ta na classe Order"
	private List<Order> orders = new ArrayList<>();

	
	public List<Order> getOrders() {
		return orders;
	}

	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
