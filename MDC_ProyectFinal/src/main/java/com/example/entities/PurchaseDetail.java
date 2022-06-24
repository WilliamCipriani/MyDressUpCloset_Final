package com.example.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase_details")
public class PurchaseDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="amount", nullable = false)
    private Float amount;

    @Column(name= "igv", nullable = false)
    private Float igv;
    
    @Column(name= "shipcost", nullable = false)
    private Float shipcost;

    @ManyToOne
    @JoinColumn(name = "Product_id", nullable = false)
    private Product product;
    
    @OneToOne
    @JoinColumn(name = "Purchase_id", nullable = false)
    private Purchase purchase;

    public Long getId() {
        return id;
    }

    public PurchaseDetail setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Float getAmount() {
        return amount;
    }

    public PurchaseDetail setAmount(Float amount) {
        this.amount = amount;
        return this;
    }
    
    public Float getIgv() {
        return igv;
    }

    public PurchaseDetail setIgv(Float igv) {
        this.igv = igv;
        return this;
    }
    
    public Float getShipcost() {
        return shipcost;
    }

    public PurchaseDetail setShipcost(Float shipcost) {
        this.shipcost = shipcost;
        return this;
    }
    
    public Product getProduct() {
        return product;
    }

    public PurchaseDetail setProduct(Product product) {
        this.product = product;
        return this;
    }
    
    public Purchase getPurchase() {
        return purchase;
    }

    public PurchaseDetail setPurchase(Purchase purchase) {
        this.purchase = purchase;
        return this;
    }
}