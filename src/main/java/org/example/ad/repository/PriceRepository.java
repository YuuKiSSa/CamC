package org.example.ad.repository;

import org.example.ad.model.Price;
import org.example.ad.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByCameraId(Long cameraId);
    List<Price> findByCameraIdAndPlatform(Long cameraId, Platform platform);
}
