package gr.uniwa.bank_api.Service;

import gr.uniwa.bank_api.DTO.AccountDTO;
import java.util.List;

//interface that contains logic methods used in controller
public interface AccountService {
    List<AccountDTO> displayAll();

    void createNewAccount(AccountDTO accountDTO);

    void addMoneyToAccount(int accountId,double amount) throws Exception;

    void removeMoneyFromAccount(int accountId,double amount) throws Exception;

    void changeStatusOfAccount(int accountID,boolean isActive);

    AccountDTO displayAccountWithId(int accountId);

    List<AccountDTO> displayAccountsOfCustomerWithIdentityNum(String idNumber);

    void deleteAccountWithAccountId(int accountID);

}
