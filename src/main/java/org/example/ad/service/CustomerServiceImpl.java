package org.example.ad.service;

import org.example.ad.DTO.CameraListDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Tag;
import org.example.ad.repository.CameraImageRepository;
import org.example.ad.repository.CameraRepository;
import org.example.ad.repository.CustomerRepository;
import org.example.ad.repository.TagRepository;
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
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private CameraImageRepository cameraImageRepository;


    @Override
    public List<CameraListDTO> findAll() {
        return cameraRepository.findAll().stream()
                .map(c -> {
                    return new CameraListDTO(c, cameraImageRepository.findUrlByCameraId(c.getId()));
                })
                .toList();
    }

    @Override
    public Optional<Camera> findById(Long id) {
        return cameraRepository.findById(id);
    }
    
    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public String findImageByCameraId(Long id){
        return cameraImageRepository.findUrlByCameraId(id);
    }
}
