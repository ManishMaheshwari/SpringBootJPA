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
@Table(name = "addresses")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cust_id")
	private Customer customer;
	 
	@Column(name = "address")
	private String address;

	public Address() {
		
	}
	public Address(String address) {
		super();
		this.address = address;
	}
	
	public Address(Customer customer, String address) {
		super();
		this.customer = customer;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
