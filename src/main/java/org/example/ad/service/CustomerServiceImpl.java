package org.example.ad.service;

import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.repository.CameraRepository;
import org.example.ad.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Camera> findAll() {
        return cameraRepository.findAll();
    }

    @Override
    public Optional<Camera> findById(Long id) {
        return cameraRepository.findById(id);
    }
    
    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
