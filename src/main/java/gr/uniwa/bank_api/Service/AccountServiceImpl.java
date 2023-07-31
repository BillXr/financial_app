package gr.uniwa.bank_api.Service;

import gr.uniwa.bank_api.Converter.AccountMapper;
import gr.uniwa.bank_api.Converter.CustomerMapper;
import gr.uniwa.bank_api.DTO.AccountDTO;
import gr.uniwa.bank_api.DTO.CustomerDTO;
import gr.uniwa.bank_api.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

//this is where logic methods used in controller are filled with code
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    private CustomerService customerService;
    private CustomerMapper customerMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, CustomerService customerService, CustomerMapper customerMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<AccountDTO> displayAll() {
        return accountRepository.findAll().stream().map(account -> accountMapper.convert(account)).collect(Collectors.toList());
    }

    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        try {
            accountRepository.save(accountMapper.convert(accountDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addMoneyToAccount(int accountId, double amount) throws Exception {
        AccountDTO accountDTO = accountMapper.convert(accountRepository.findAccountByAccountId(accountId));
        if (accountDTO.isStatus() && accountDTO.getBalance() >= 0) {
            accountDTO.setBalance(accountDTO.getBalance() + amount);
            accountRepository.save(accountMapper.convert(accountDTO));
        } else throw new Exception();
    }

    @Override
    public void removeMoneyFromAccount(int accountId, double amount) throws Exception {
        AccountDTO accountDTO = accountMapper.convert(accountRepository.findAccountByAccountId(accountId));
        if (accountDTO.isStatus() && accountDTO.getBalance() - amount >= 0) {
            accountDTO.setBalance(accountDTO.getBalance() - amount);
            accountRepository.save(accountMapper.convert(accountDTO));
        } else throw new Exception();
    }

    @Override
    public void changeStatusOfAccount(int accountID, boolean isActive) {
        AccountDTO accountDTO = accountMapper.convert(accountRepository.findAccountByAccountId(accountID));
        accountDTO.setStatus(isActive);
        accountRepository.save(accountMapper.convert(accountDTO));
    }

    @Override
    public AccountDTO displayAccountWithId(int accountId) {
        return accountMapper.convert(accountRepository.findAccountByAccountId(accountId));
    }

    @Override
    public List<AccountDTO> displayAccountsOfCustomerWithIdentityNum(String idNumber) {
        CustomerDTO customerDTO = customerService.findByIdNumber(idNumber);
        return accountRepository
                .findAccountsByCustomer(customerMapper.convert(customerDTO))
                .stream().map(account -> accountMapper.convert(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccountWithAccountId(int accountID) {
        AccountDTO accountDTO = accountMapper.convert(accountRepository.findAccountByAccountId(accountID));
        if (accountDTO.getCustomer() != null) {
            accountRepository.delete(accountMapper.convert(accountDTO));
        } else throw new RuntimeException();
    }

}
