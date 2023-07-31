package gr.uniwa.bank_api.Controller;

import gr.uniwa.bank_api.DTO.AccountDTO;
import gr.uniwa.bank_api.DTO.CustomerDTO;
import gr.uniwa.bank_api.Service.AccountService;
import gr.uniwa.bank_api.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//endpoints of rest api started from /account
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping("/all") //creates /account/all endpoint
    public List<AccountDTO> showAllAccountsOnBank() {
        return accountService.displayAll();
    } //return all accounts

    @PostMapping("/new") //creates /account/new endpoint
    public ResponseEntity<String> addNewAccountForCustomer(@RequestBody AccountDTO accountDTO) { //get a json object as argument
        try {
            CustomerDTO customerDTO = customerService.findByIdNumber(accountDTO.getCustomer().getIdNumber()); //tries to find customer by identity
            accountDTO.setCustomer(customerDTO); //if found set this customer to account
            accountService.createNewAccount(accountDTO); //and create account
            return ResponseEntity.ok("Account added!"); //return success msg
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("We could not find this customer. Please register!"); //if not found return error msg
        }
    }

    @PutMapping("/deposit/{accountId}") //creates /account/deposit/{id} endpoint
    public ResponseEntity<String> depositMoney(@PathVariable int accountId, //get a path parameter
                                               @RequestBody double amount) { //get a json object as argument
        if (amount > 0.0) { //validate amount
            try {
                accountService.addMoneyToAccount(accountId, amount); //try to add money to account
                return ResponseEntity.ok("Successful deposition!"); //success msg
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Unsuccessful request! Please check your account id given!"); //if exception found return error msg
            }
        } else {
            return ResponseEntity.badRequest().body("Amount can not be below zero!"); //validation fail msg
        }
    }

    @PutMapping("/withdraw/{accountId}") //creates /account/withdraw/{id} endpoint
    public ResponseEntity<String> withdrawMoney(@PathVariable int accountId, //get a path parameter
                                                @RequestBody double amount) { //get a json object as argument
        if (amount > 0.0) { //validate amount
            try {
                accountService.removeMoneyFromAccount(accountId, amount); //try to remove money to account
                return ResponseEntity.ok("Successful withdraw!"); //success msg
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Unsuccessful request! Please check your account id given or your balance!"); //if exception found return error msg
            }
        } else {
            return ResponseEntity.badRequest().body("Amount can not be below zero!"); //validation fail msg
        }
    }

    @PutMapping("/transfer") //creates /account/transfer?from={id1}&to={id2} endpoint
    public ResponseEntity<String> withdrawMoney(@RequestParam int from, //get a request param
                                                @RequestParam int to, //get a request param
                                                @RequestBody double amount) { //get a json object as argument
        if (amount > 0.0 && from != to) { //validate amount & accounts are different
            try {
                accountService.removeMoneyFromAccount(from, amount); //try to remove money to account
                accountService.addMoneyToAccount(to, amount); //try to add money to account
                return ResponseEntity.ok("Successful transfer!"); //success msg
            } catch (Exception e) {
                try {
                    accountService.addMoneyToAccount(from, amount); //if exception found try to add money back to account
                } catch (Exception ex) {
                    return ResponseEntity.badRequest().body("Invalid inputs!"); //if exception found return error msg
                }
                return ResponseEntity.badRequest().body("Unsuccessful request! Please check account ids given or your balance!"); //if exception found return error msg
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid inputs!"); //validation fail msg
        }
    }

    @PutMapping("{accountID}") //creates /account/{id}?isActive=(true/false) endpoint
    public ResponseEntity<String> setStatusOfAccount(@PathVariable int accountID, //get a path parameter
                                                     @RequestParam boolean isActive) { //get a request param
        try {
            accountService.changeStatusOfAccount(accountID, isActive); //try to change status
            return ResponseEntity.ok("Status changed!"); //success msg
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unsuccessful request! Please check account id given!"); //if exception found return error msg
        }
    }

    @GetMapping("{accountID}") //creates /account/{id} endpoint
    public ResponseEntity<Object> showAccountById(@PathVariable int accountID){ //get a path parameter
        try{
            return ResponseEntity.ok().body(accountService.displayAccountWithId(accountID)); //try to display account based on id , and return it
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Unsuccessful request! Please check account id given!"); //if exception found return error msg
        }
    }

    @GetMapping("customer") //creates /account/customer?identity={idNum} endpoint
    public ResponseEntity<Object> showAccountByCustomerIdentity(@RequestParam String identity){ //get a request param
        try{
            return ResponseEntity.ok().body(accountService.displayAccountsOfCustomerWithIdentityNum(identity)); //try to display accounts based on customer identity number given , and return it
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Unsuccessful request! Please check account id given!"); //if exception found return error msg
        }
    }

    @DeleteMapping("{accountID}") //creates /account/{id} endpoint
    public ResponseEntity<String> deleteAccountById(@PathVariable int accountID){ //get a path parameter
        try{
            accountService.deleteAccountWithAccountId(accountID); //try to delete account with given id
            return ResponseEntity.ok().body("Successful deletion of account!"); //success msg
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Unsuccessful request! Please check account id given!"); //if exception found return error msg
        }
    }

}
