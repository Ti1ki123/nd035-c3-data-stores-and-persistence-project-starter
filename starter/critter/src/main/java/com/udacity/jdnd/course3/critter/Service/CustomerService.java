package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Repositoty.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repositoty.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {

        List<Customer> returnValue = new ArrayList<>();
        returnValue = customerRepository.findAll();
        returnValue.forEach(customer -> customer.setPets(petRepository.findByOwnerUserId(customer.getUserId())));

        if (returnValue.isEmpty()) {
            throw new RuntimeException("Do not have any customer which registered.");
        }

        return returnValue;
    }
}
