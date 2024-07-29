package org.example.ad;

import java.util.List;

import org.example.ad.model.Camera;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface CustomerService {

	public List<Camera> findAll();

	
}
