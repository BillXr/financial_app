package gr.uniwa.bank_api.Service;

import gr.uniwa.bank_api.Converter.CustomerMapper;
import gr.uniwa.bank_api.DTO.CustomerDTO;
import gr.uniwa.bank_api.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//this is where logic methods used in controller are filled with code
@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        try {
            customerRepository.save(customerMapper.convert(customerDTO));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public CustomerDTO findByIdNumber(String idNum) {
        return customerMapper.convert(customerRepository.findCustomerByIdNumber(idNum));
    }

}
