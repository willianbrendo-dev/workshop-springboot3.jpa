package com.example.aulajpa.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// --- Chave Primária (One-to-One - Dependente) ---
    // O ID de Payment será o mesmo ID da Order associada.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
 // Atributo 'moment' (data/hora do pagamento)
    // Usamos Instant (em vez de Date, como no UML) por ser a boa prática no Java moderno.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;
    
 // --- Relacionamento One-to-One (Dependente) ---
    // @OneToOne: Define o relacionamento com Order
    // @MapsId: 🎯 Indica que o ID desta entidade (Payment.id) será usado para mapear o ID da entidade Order
    // @JsonIgnore: Evita que o Payment tente serializar o Order, que tentaria serializar o Payment (loop).
    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;
    
    public Payment() {
    }

	public Payment(Instant moment, Order order) {
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
