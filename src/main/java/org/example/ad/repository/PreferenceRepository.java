package org.example.ad.repository;

import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Preference;
import org.example.ad.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
	Optional<Preference> findByCustomerAndTag(Customer customer, Tag tag);

	@Query("SELECT c FROM Camera c " + "JOIN c.tags t " + "JOIN Preference p ON t.id = p.tag.id "
			+ "JOIN Customer cust ON p.customer.id = cust.id " + "WHERE cust.username = :username " + "GROUP BY c.id "
			+ "ORDER BY SUM(p.amount) DESC")
	List<Camera> findByUserName(String username);

	@Query("SELECT c.id AS cameraId, SUM(p.amount) as totalAmount " + "FROM Preference p " + "JOIN p.tag t "
			+ "JOIN t.cameras c " + "WHERE p.customer.id = :customerId " + "GROUP BY c.id "
			+ "ORDER BY totalAmount DESC")
	List<Object[]> findMostPreferredCameraIdByCustomer(@Param("customerId") Long customerId);

}
