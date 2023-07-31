package gr.uniwa.bank_api.Service;

import gr.uniwa.bank_api.DTO.CustomerDTO;

//interface that contains logic methods used in controller
public interface CustomerService {

    void createCustomer(CustomerDTO customerDTO);

    CustomerDTO findByIdNumber(String idNum);

}
