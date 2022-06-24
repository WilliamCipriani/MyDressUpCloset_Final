package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(
			name = "products-sequence-generator",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@Parameter(name = "sequence_name", value = "products_sequence"),
					@Parameter(name = "initial_value", value = "100"),
					@Parameter(name = "increment_size", value = "1")
			})
	
	
	@GeneratedValue(generator = "products-sequence-generator")
	@Column(nullable=false, updatable=false)
	private Long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "brand", nullable = false, length = 20)
	private String brand;

	@Column(name = "price", nullable = false)
	private Float price;

	@Column(name = "size", nullable = false, length = 20)
	private String size;
	
	@Column(name = "gender", nullable = false, length = 20)
	private String gender;

	@Column(name = "category", nullable = false, length = 20)
	private String category;

	@Column(name = "type", nullable = false, length = 20)
	private String type;

	@Column(name = "color", nullable = false)
	private String color;

	@Column(name = "url", nullable = false)
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getGen() {
		return gender;
	}

	public void setGen(String gen) {
		this.gender = gen;
	}

	public String getCate() {
		return category;
	}

	public void setCate(String cate) {
		this.category = cate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}
