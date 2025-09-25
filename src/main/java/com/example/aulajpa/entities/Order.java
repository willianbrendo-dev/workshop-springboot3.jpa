package com.example.aulajpa.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.aulajpa.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	private Integer orderStatus;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;

	// --- Associação OneToMany (Order tem muitos OrderItems - Items) ---
	// mappedBy: Indica qual é o campo na classe OrderItem que mapeia esta
	// associação.
	// O mapeamento está no lado da chave composta OrderItemPK.order (que acessamos
	// via OrderItem.getOrder()).
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>(); // Instancia a coleção para garantir que nunca será nula

	// --- Relacionamento One-to-One (Dono da Chave) ---
	// mappedBy: Indica que o mapeamento da chave estrangeira está na classe Payment
	// (no campo 'order').
	// cascade = CascadeType.ALL: Define que, ao salvar ou deletar um Order, o
	// Payment associado deve seguir.
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client, Payment payment) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
		this.payment = payment;
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
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

// --- Lógica de Negócio: Método total() ---

	// O método total() foi definido no UML.
	// Ele calcula a soma dos subtotais de todos os itens do pedido.
	public Double getTotal() {
		double sum = 0.0;
		// Percorre a coleção de itens
		for (OrderItem item : items) {
			// Soma o subtotal de cada item (preço * quantidade)
			sum += item.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

}
