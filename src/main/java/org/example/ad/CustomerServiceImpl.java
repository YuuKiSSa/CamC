package org.example.ad;

import java.util.List;

import org.example.ad.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Camera> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

}
