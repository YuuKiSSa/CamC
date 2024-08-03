package org.example.ad.service;

import org.example.ad.model.Customer;
import org.example.ad.repository.AccountRepository;
import org.example.ad.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Override
    public Customer register(String username, String rawPassword) {
        String encryptedPassword = passwordEncoder.encode(rawPassword);  
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(encryptedPassword);
        return accountRepository.save(customer);  
    }

    @Override
    public Customer login(String username, String rawPassword) {
        Customer customer = accountRepository.findByUsername(username).orElse(null);
        if (customer != null && passwordEncoder.matches(rawPassword, customer.getPassword())) {
            return customer; 
        }
        return null;  
    }
}
