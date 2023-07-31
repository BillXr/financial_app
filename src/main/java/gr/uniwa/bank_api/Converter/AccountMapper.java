package gr.uniwa.bank_api.Converter;

import gr.uniwa.bank_api.DTO.AccountDTO;
import gr.uniwa.bank_api.Model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//mapper is used to convert dto to model and model to dto , in order to "play" with data between services and repository
@Component //this is used to automatically detect beans
public class AccountMapper {

    private CustomerMapper customerMapper;

    @Autowired //automatic dependency injection , autowires relationships between collab beans
    public AccountMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public AccountDTO convert(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setStatus(account.isStatus());
        accountDTO.setCustomer(customerMapper.convert(account.getCustomer()));

        return accountDTO;
    }

    public Account convert(AccountDTO account){
        Account acc = new Account();
        acc.setAccountId(account.getAccountId());
        acc.setBalance(account.getBalance());
        acc.setStatus(account.isStatus());
        acc.setCustomer(customerMapper.convert(account.getCustomer()));

        return acc;
    }

}
