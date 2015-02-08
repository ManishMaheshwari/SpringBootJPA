package manish.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "orders")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cust_id")
	private Customer customer;

	@Column(name = "description")
	private String description;

	public OrderItem() {

	}
	
	public OrderItem(String description) {
		super();
		this.description = description;
	}

	public OrderItem(Customer customer, String description) {
		super();
		this.customer = customer;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
