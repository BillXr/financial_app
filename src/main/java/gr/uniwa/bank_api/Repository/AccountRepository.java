package gr.uniwa.bank_api.Repository;

import gr.uniwa.bank_api.Model.Account;
import gr.uniwa.bank_api.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

//creates the connection with database
@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> { //extension is used to have a bunch of methods

    Account findAccountByAccountId(int id);

    List<Account> findAccountsByCustomer(Customer customer);

}
