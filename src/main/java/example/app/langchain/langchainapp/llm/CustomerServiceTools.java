package example.app.langchain.langchainapp.llm;

import dev.langchain4j.agent.tool.Tool;
import example.app.langchain.langchainapp.service.Customer;
import example.app.langchain.langchainapp.service.CustomerService;

import java.util.List;

public class CustomerServiceTools {
    private CustomerService customerService;

    public CustomerServiceTools(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Tool
    public List<Customer> findCustomer(String email, String correlationId) {
       return customerService.findCustomer(email, correlationId);
    }

    @Tool
    public void updateAddress(String correlationId, String address) {
        customerService.updateAddress(correlationId, address);
    }

    @Tool
    public List<Customer> retrieveAllApplications() {
        return customerService.retrieveAllApplications();
    }
}
