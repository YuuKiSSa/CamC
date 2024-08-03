package org.example.ad.repository;

import org.example.ad.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
