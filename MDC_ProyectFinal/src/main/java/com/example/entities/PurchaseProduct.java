package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "purchase_product")
public class PurchaseProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPuchaseProduct;

	private int quantity;

	@Transient
	private Float subtotal;

	@ManyToOne
	@JoinColumn(name = "purchase_id", nullable = false)
	private Purchase purchase;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public Long getIdPuchaseProduct() {
		return idPuchaseProduct;
	}

	public void setIdPuchaseProduct(Long idPuchaseProduct) {
		this.idPuchaseProduct = idPuchaseProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Float getSubtotal() {
		Float subtotalAux = quantity * product.getPrice();
		return subtotalAux;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
