package gr.uniwa.bank_api.Controller;

import gr.uniwa.bank_api.DTO.CustomerDTO;
import gr.uniwa.bank_api.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/new") //creates /customer/new endpoint
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) { //get a json object as argument
        try{
            CustomerDTO dto = customerService.findByIdNumber(customerDTO.getIdNumber()); //tries to find customer by identity
            return ResponseEntity.badRequest().body("Customer already exists!"); //if found returns error msg
        }catch (NullPointerException e){
            customerService.createCustomer(customerDTO); //if not found creates customer
            return ResponseEntity.ok("Customer added!"); //returns success msg
        }
    }

}
