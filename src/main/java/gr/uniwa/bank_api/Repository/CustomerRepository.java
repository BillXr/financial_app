package gr.uniwa.bank_api.Repository;

import gr.uniwa.bank_api.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//creates the connection with database
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> { //extension is used to have a bunch of methods

    Customer findCustomerByIdNumber(String idNum);

}
