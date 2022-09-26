package com.vini.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categorys")
public class Category implements Serializable{ // Serializable serve para que o objeto trafegue na rede, possa
	// ser gravado em arquivods, e por ai vai.
	private static final long serialVersionUID = 1L;
	
	@Id // Dizendo qual campo vai ser a PK(Primary Key)
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Incrementando automaticamente o ID.
	private Long id;
	private String name;
	
	@JsonIgnore // Serve para não dar loops, categorias chamando produtos, produtos chamando categorias...
	@ManyToMany(mappedBy = "categories") // Relação muito para muitos, relacionando a coleção que esta na classe Product.
	private Set<Product> products = new HashSet<>();
	
	public Category() {
	}

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
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
	
	public Set<Product> getProducts() {
		return products;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
}
