package gr.uniwa.bank_api.Converter;

import gr.uniwa.bank_api.DTO.CustomerDTO;
import gr.uniwa.bank_api.Model.Customer;
import org.springframework.stereotype.Component;

//mapper is used to convert dto to model and model to dto , in order to "play" with data between services and repository
@Component //this is used to automatically detect beans
public class CustomerMapper {

    public CustomerDTO convert(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setFname(customer.getFname());
        customerDTO.setLname(customer.getLname());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setBirthdate(customer.getBirthdate());
        customerDTO.setIdNumber(customer.getIdNumber());

        return customerDTO;
    }

    public Customer convert(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setFname(customerDTO.getFname());
        customer.setLname(customerDTO.getLname());
        customer.setAddress(customerDTO.getAddress());
        customer.setBirthdate(customerDTO.getBirthdate());
        customer.setIdNumber(customerDTO.getIdNumber());

        return customer;
    }

}
