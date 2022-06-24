package com.example.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="my_products")
public class MyProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMyProduct;
	
	private int quantity;
	
	@Transient
	private Float subtotal;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
	
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
	
	
	public MyProduct(Long idMyProduct) {
		super();
		this.idMyProduct = idMyProduct;
	}

	public MyProduct() {
		super();
		
	}

	public Long getIdMyProduct() {
		return idMyProduct;
	}

	public void setIdMyProduct(Long idMyProduct) {
		this.idMyProduct = idMyProduct;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
}
