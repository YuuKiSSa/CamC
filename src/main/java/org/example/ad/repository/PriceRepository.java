package org.example.ad.repository;

import org.example.ad.model.Price;
import org.example.ad.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
	List<Price> findByCameraId(Long cameraId);

	List<Price> findByCameraIdAndPlatform(Long cameraId, Platform platform);

	@Query("SELECT MIN(p.price) FROM Price p WHERE p.camera.id = :cameraId AND p.date = (SELECT MAX(p2.date) FROM Price p2 WHERE p2.camera.id = :cameraId)")
	Double findLatestLowestPriceByCameraId(@Param("cameraId") Long cameraId);
}