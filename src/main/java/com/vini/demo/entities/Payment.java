package com.vini.demo.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "payment")
public class Payment {
	
	@Id // Dizendo qual campo vai ser a PK(Primary Key)
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Incrementando automaticamente o ID.
	private Long id;
	
	
	// Para garantir que meu instante seja mostrado la no Json no formato da ISO 8601, usa este comando.
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	@OneToOne
	@MapsId // @MapsId é usada para especificar que o identificador de entidade é mapeado pelo @ManyToOne atualmente anotado ou @OneToOne associado
	private Order order;

	public Payment() {
	}

	public Payment(Long id, Instant moment, Order order) {
		this.id = id;
		this.moment = moment;
		this.order = order;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
	
}
