package org.example.ad.repository;

import java.util.List;

import org.example.ad.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
	void deleteByCameraIdAndCustomerId(Long cameraId, Long customerId);
	
	List<Favorite> findByCustomerId(Long customerId);
}
