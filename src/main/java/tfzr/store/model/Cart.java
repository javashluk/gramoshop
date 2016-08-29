package tfzr.store.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Cart implements Serializable{
	
	private static final long serialVersionUID = 8228655347944302255L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private User user;

	@ManyToOne
	private Product product;
	
	@Column
	private Integer quantity;

	public static long getSerialVersionUID() {

		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
