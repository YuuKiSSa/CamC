package org.example.ad.service.impl;

import java.util.List;

import org.example.ad.model.Admin;
import org.example.ad.repository.AdminRepository;
import org.example.ad.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Admin login(String id, String password) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return admin;
        } else {
            return null;
        }
    }

    @PostConstruct
    public void encryptAllPasswords() {
        List<Admin> admins = adminRepository.findAll();
        for (Admin admin : admins) {
        	if (!admin.getPassword().startsWith("$2a$")) {
        	    admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        	    adminRepository.save(admin);
        	}
        }
    }
}
