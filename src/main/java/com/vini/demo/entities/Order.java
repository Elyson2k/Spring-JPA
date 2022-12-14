package com.vini.demo.entities;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vini.demo.entities.enums.OrderStatus;

@Entity
@Table(name = "orders")
public class Order implements Serializable{ // Serializable serve para que o objeto trafegue na rede, possa
	// ser gravado em arquivods, e por ai vai.
	private static final long serialVersionUID = 1L;	
	
	
	@Id // Dizendo qual campo vai ser a PK(Primary Key)
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Incrementando automaticamente o ID.
	private Long id;	
	
	
	// Para garantir que meu instante seja mostrado la no Json no formato da ISO 8601, usa este comando.
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	
	private Integer orderStatus;
	
	
	@ManyToOne // Fazendo uma relação muitos para um.
	@JoinColumn(name = "client_id") // Dando o nome da columa de "client_id".
	private User client;
	
	
	@OneToMany(mappedBy = "id.order") // Relaçao um p muitos.
	private Set<OrderItem> items = new HashSet<>();
	
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // Relação um p um tem que mapiar as duas entidades para ter o mesmo ID.
	private Payment payment;
	
	
	
	public Order() {
	}

	
	
	public Order(Long id, Instant moment,OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus.getCode();
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Double getTotal() {
		Double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
	}
	
	
	public Set<OrderItem> getItems(){
		return items;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
