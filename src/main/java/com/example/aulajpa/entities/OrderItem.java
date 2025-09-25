package com.example.aulajpa.entities;

import java.io.Serializable;
import java.util.Objects;

import com.example.aulajpa.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// --- Chave Primária Composta ---
    // Usa a anotação @EmbeddedId para dizer ao JPA que a chave primária
    // é um objeto da classe OrderItemPK.
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK(); // Instancia para evitar NullPointerException
    
 // --- Atributos Próprios da Associação (citados no diagrama) ---
    private Integer quantity; // Quantidade de um produto específico neste pedido
    private Double price;    // Preço do produto no momento em que o item foi adicionado (importante para histórico)

    public OrderItem() {
    }

 // Construtor com argumentos para os atributos da associação
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        // Configura as chaves estrangeiras dentro do objeto OrderItemPK
        id.setOrder(order); 
        id.setProduct(product);
        
        // Configura os atributos próprios
        this.quantity = quantity;
        this.price = price;
    }
    
    

// --- Métodos Auxiliares para Acessar Order e Product (via OrderItemPK) ---
    
    // @JsonIgnore: Evita que o OrderItem tente serializar o Order, que por sua vez 
    // tentaria serializar o OrderItem (loop infinito). O JSON deve ignorar esta referência.
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

    // --- Getter para o Subtotal (método de negócio citado no diagrama) ---
    public Double getSubTotal() {
        // O subtotal é o preço de um item multiplicado pela sua quantidade
        return price * quantity;
    }

    // --- Getters e Setters dos Atributos Próprios ---
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

    // --- Implementação de hashCode e equals ---
    // Estes métodos devem ser baseados no objeto 'id' (OrderItemPK),
    // pois a chave primária é quem define a identidade do objeto no JPA.
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
