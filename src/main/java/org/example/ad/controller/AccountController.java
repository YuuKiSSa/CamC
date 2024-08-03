package org.example.ad.controller;

import org.example.ad.DTO.LoginDTO;
import org.example.ad.model.Customer;
import org.example.ad.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDetails, HttpSession session) {
        Customer customer = accountService.login(loginDetails.getUsername(), loginDetails.getPassword());
        if (customer != null) {
            session.setAttribute("user", customer);
            return ResponseEntity.ok().body("Logged in successfully");
        } else {
            return ResponseEntity.status(401).body("Unauthorized - Incorrect username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginDTO registrationDetails) {
        Customer customer = accountService.register(registrationDetails.getUsername(), registrationDetails.getPassword());
        if (customer != null) {
            return ResponseEntity.ok("Registration successful");
        } else {
            return ResponseEntity.badRequest().body("Registration failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        try {
            session.invalidate();
            return ResponseEntity.ok("Logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error - Unable to log out");
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        Customer currentUser = (Customer) session.getAttribute("user");
        if (currentUser != null) {
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.status(401).body("Unauthorized - No user logged in");
        }
    }
}
