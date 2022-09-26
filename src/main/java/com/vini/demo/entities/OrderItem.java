package com.vini.demo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vini.demo.entities.pk.OrdemItemPK;


@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{ // Serializable serve para que o objeto trafegue na rede, possa
	// ser gravado em arquivods, e por ai vai.
	private static final long serialVersionUID = 1L;
	
	
	/* Sempre que você for criar uma classe auxiliar que é um ID composto, você tem que instanciar ela 
	 * como esta abaixo. */
	
	@EmbeddedId
	private OrdemItemPK id = new OrdemItemPK();
	
	private Integer quantity;
	private Double price;
	public OrderItem() {		
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		// ids estão valendo null;
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSubTotal() {
		return price * quantity;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
