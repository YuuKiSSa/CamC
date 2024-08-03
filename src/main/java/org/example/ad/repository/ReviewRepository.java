package org.example.ad.repository;

import org.example.ad.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCameraId(Long cameraId);
}
