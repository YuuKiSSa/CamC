package org.example.ad.service;

import org.example.ad.model.Admin;

public interface AdminService {
    Admin login(String id, String password);
}
