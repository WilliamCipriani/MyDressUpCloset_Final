package com.example.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="purchases")
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name="amount", nullable = false)
    private Float amount;
    
	@ManyToOne
	@JoinColumn(name = "idCard")
	private Card card;

    @ManyToOne
    @JoinColumn(name = "User_id", nullable = false)
    private Users user;

    public Long getId() {
        return id;
    }

    public Purchase setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Purchase setDate(Date date) {
        this.date = date;
        return this;
    }

    public Float getAmount() {
        return amount;
    }

    public Purchase setAmount(Float amount) {
        this.amount = amount;
        return this;
    }

	public Users getUser() {
		return user;
	}

	public Purchase setUser(Users user) {
		this.user = user;
		return this;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Purchase() {
		super();
		card = new Card();
	}

	
}