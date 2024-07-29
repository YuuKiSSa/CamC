package org.example.ad.repository;

import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Preference;
import org.example.ad.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Optional<Preference> findByCustomerAndTag(Customer customer, Tag tag);

    @Query("SELECT c FROM Camera c " +
            "JOIN c.tags t " +
            "JOIN Preference p ON t.id = p.tag.id " +
            "JOIN Customer cust ON p.customer.id = cust.id " +
            "WHERE cust.username = :username " +
            "GROUP BY c.id " +
            "ORDER BY SUM(p.amount) DESC")
	List<Camera> findByUserName(String username);
    
}
