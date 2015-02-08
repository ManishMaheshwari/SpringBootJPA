package manish;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import manish.entities.Address;
import manish.entities.Customer;
import manish.entities.OrderItem;
import manish.repo.AddressRepository;
import manish.repo.CustomerRepository;
import manish.repo.OrderRepository;

@Component
@Order(value=1)
public class JpaStart implements CommandLineRunner {

	@Autowired
	public CustomerRepository customerRepo;
	
	@Autowired
	public OrderRepository orderRepo;
	
	@Autowired
	public AddressRepository addressRepo;
	
	@Override
	public void run(String... args) throws Exception {
		//populate();

		// fetch all customers
		System.out.println("Customers found with findAll():");
		printCustomer(customerRepo.findAll());
		
		// fetch customers by last name
		System.out.println("Customer found with findByLastName('Bauer'):");
		printCustomer(customerRepo.findByLastName("Bauer"));
		
		// fetch customers by first name and last name
		System.out.println("Customer found with findByFirstNameAndLastName('MJack', 'MBauer'):");
		printCustomer(customerRepo.findByFirstNameAndLastName("MJack", "MBauer"));
		
		// fetch customers by first name and last name
		System.out.println("Customer found with findByFirstNameOrLastNameOrderByFirstNameAsc('MJack', 'Bauer'):");
		printCustomer(customerRepo.findByFirstNameOrLastNameOrderByFirstNameAsc("MJack", "Bauer"));

		// fetch customers defined by JAP @Query
		System.out.println("Customer found with findLastName('Bauer'):");
		printCustomer(customerRepo.findLastName("Bauer"));

		// fetch customers defined by Native @Query
		System.out.println("Customer found with findEveryone(2):");
		printCustomer(customerRepo.findEveryone(2));

		// fetch customers defined by Named Parameter @Query
		System.out.println("Customer found with findLastNamedParam('ZBauer'):");
		printCustomer(customerRepo.findLastNamedParam("ZBauer"));
		
		//Modifying, and Transactional
		customerRepo.replaceLastName("MBauer", "Manish");
		
		// fetch customers by EntityGraph
		System.out.println("Customer found with findAllDetails():");
		printCustomer(customerRepo.findAllDetails());
	}

	private void printCustomer(Iterable<Customer> iterable) {
		for (Customer m : iterable) {
			System.out.println(m);
		}
	}

	private void populate() {
		Customer c1 = new Customer("MJack", "MBauer");
		customerRepo.save(c1);
		HashSet<Address> c1Addresses = new HashSet<Address>(Arrays.asList(new Address(c1, "Mumbai"), new Address(c1, "Madras")));
		addressRepo.save(c1Addresses);
		HashSet<OrderItem> c1OrderItems = new HashSet<OrderItem>(Arrays.asList(new OrderItem(c1, "M1"), new OrderItem(c1, "M2")));
		orderRepo.save(c1OrderItems);
		
		Customer c2 = new Customer("BJack", "Bauer");
		customerRepo.save(c2);
		HashSet<Address> c2Addresses = new HashSet<Address>(Arrays.asList(new Address(c2, "Bangalore"), new Address(c2, "Brussels")));
		addressRepo.save(c2Addresses);
		HashSet<OrderItem> c2OrderItems = new HashSet<OrderItem>(Arrays.asList(new OrderItem(c2, "B1"), new OrderItem(c2, "B2")));
		orderRepo.save(c2OrderItems);
		
		Customer c3 = new Customer("ZJack", "ZBauer");
		customerRepo.save(c3);
		HashSet<Address> c3Addresses = new HashSet<Address>(Arrays.asList(new Address(c3, "Zurich"), new Address(c3, "Zoe")));
		addressRepo.save(c3Addresses);
		HashSet<OrderItem> c3OrderItems = new HashSet<OrderItem>(Arrays.asList(new OrderItem(c3, "Z1"), new OrderItem(c3, "Z2")));
		orderRepo.save(c3OrderItems);
		
	}

}
