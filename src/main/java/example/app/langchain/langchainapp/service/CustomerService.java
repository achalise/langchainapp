package example.app.langchain.langchainapp.service;

import opennlp.tools.util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    private final Collection<Customer> customers = new ArrayList<>();
    public CustomerService() {
        customers.add(new Customer("18666A", "curtis@gmail.com", "Brock", "Curtis", "76 Chalmers Ave, Redfern 2119", "FLOODS", 2000, "SUBMITTED"));
        customers.add(new Customer("19966A", "curtis@gmail.com", "Brock", "Curtis", "76 Chalmers Ave, Redfern 2119", "BUSH_FIRE", 550, "SUBMITTED"));
        customers.add(new Customer("21966A", "curtis@gmail.com", "Brock", "Curtis", "76 Chalmers Ave, Redfern 2119", "HAIL_DAMAGE", 250, "SUBMITTED"));
        customers.add(new Customer("36559B","blogg@gmail.com", "Joe", "Blogg", "40 Bridge Road, Sydney, 2000", "BUSH_FIRE", 300, "APPROVED"));
        customers.add(new Customer("91137D", "duran@gmail.com","Leandro", "Duran", "199 George Street, Newtown, 2000", "HAIL_DAMAGE", 300, "PAYMENT_SUBMITTED"));

    }

    public List<Customer> findCustomer(String email, String correlationId) {
        if (email != null && !StringUtil.isEmpty(email)) {
            return getCustomerApplicationsByEmail(email);
        }
        return getCustomerApplicationsByCorrelationid(correlationId);
    }

    public void updateAddress(String correlationId, String address) {
        List<Customer> customerList = getCustomerApplicationsByCorrelationid(correlationId);
        Customer customer = customerList.getFirst();
        customers.remove(customer);
        customers.add(new Customer(correlationId, customer.email(), customer.firstName(), customer.lastName(),
                address, customer.claimType(), customer.amount(), customer.status()));
    }

    public List<Customer> retrieveAllApplications() {
        return customers.stream().toList();
    }

    private List<Customer> getCustomerApplicationsByEmail(String email) {
        System.out.println(STR."Finding customer with email \{email}");
        var custList = customers.stream().filter(customer -> customer.email().equals(email)).toList();
        if (custList.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        return custList;
    }

    private List<Customer> getCustomerApplicationsByCorrelationid(String correlationId) {
        System.out.println(STR."Finding customer with correlationId \{correlationId}");
        var custList = customers.stream().filter(customer -> customer.correlationId().equals(correlationId)).toList();
        if (custList.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        return custList.stream().filter(c -> c.correlationId().equals(correlationId)).toList();
    }
}

