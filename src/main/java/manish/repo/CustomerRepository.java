package manish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import manish.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstNameAndLastName (String firstName, String LastName);
    List<Customer> findByFirstNameOrLastName (String firstName, String LastName);
    List<Customer> findByFirstNameOrLastNameOrderByFirstNameAsc (String firstName, String LastName);
    
    //JPA Query
    @Query("Select c from Customer c where c.lastName=?1")
    List<Customer> findLastName(String lastName);
    
    //Native Query
    @Query(value="Select * from customers limit ?1", nativeQuery= true)
    List<Customer> findEveryone(int howMany);
    
    //Named Parameter Query
    @Query(value="Select c from Customer c where c.lastName = :ln")
    @Transactional
    List<Customer> findLastNamedParam(@Param ("ln") String lastName);
    
    @Modifying
    @Transactional(timeout=2, isolation=Isolation.REPEATABLE_READ, propagation=Propagation.REQUIRED)
    @Query("Update Customer c set c.lastName = ?2 where c.lastName = ?1")
    int replaceLastName(String from, String to);
    
    @EntityGraph(value="CustomerInfo.withOrders", type=EntityGraphType.LOAD)
    @Query("Select c from Customer c")
    List<Customer> findAllDetails();
    
}