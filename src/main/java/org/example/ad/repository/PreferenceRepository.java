package org.example.ad.repository;

import org.example.ad.model.Customer;
import org.example.ad.model.Preference;
import org.example.ad.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Optional<Preference> findByCustomerAndTag(Customer customer, Tag tag);
}
