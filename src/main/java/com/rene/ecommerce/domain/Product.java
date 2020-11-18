package com.rene.ecommerce.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rene.ecommerce.domain.users.Client;
import com.rene.ecommerce.domain.users.Seller;

@Entity
public class Product implements Serializable {

	public Product() {
		setBuyerOfTheProduct(null);
	}
	
	
	public Product(Integer id, String name, Double price, Category category, Seller ownOfTheProduct) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.ownOfTheProduct = ownOfTheProduct;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private Double price;

	
	@ManyToOne
	@JoinTable(name = "PRODUCT_CATEGORY", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Category category;

	@ManyToOne
	@JoinTable(name = "SELLER_PRODUCT", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "seller_id"))
	private Seller ownOfTheProduct;
	
	@JsonIgnore
	@ManyToOne
	@JoinTable(name = "CLIENT_PRODUCT", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
	private Client buyerOfTheProduct;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Seller getOwnOfTheProduct() {
		return ownOfTheProduct;
	}

	public void setOwnOfTheProduct(Seller ownOfTheProduct) {
		this.ownOfTheProduct = ownOfTheProduct;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public Client getBuyerOfTheProduct() {
		return buyerOfTheProduct;
	}


	public void setBuyerOfTheProduct(Client buyerOfTheProduct) {
		this.buyerOfTheProduct = buyerOfTheProduct;
	}
	
	 
    public static boolean isSold(Product obj) {
    	
    	if(obj.getBuyerOfTheProduct() == null) {
    		return false;
    	}
    	return true;
    }


	
	

}
