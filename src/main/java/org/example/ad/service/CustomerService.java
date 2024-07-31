package org.example.ad.service;

import java.util.List;
import java.util.Optional;

import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Tag;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface CustomerService {

	public List<Camera> findAll();

	Optional<Camera> findById(Long id);

	Optional<Customer> findByUsername(String username);

	public List<String> findAllURL();

//	public List<String> findTag(Long id);
}
