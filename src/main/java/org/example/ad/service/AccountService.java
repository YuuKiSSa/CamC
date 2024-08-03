package org.example.ad.service;

import org.example.ad.model.Customer;

public interface AccountService {
    Customer register(String username, String rawPassword);
    Customer login(String username, String password);  
}
