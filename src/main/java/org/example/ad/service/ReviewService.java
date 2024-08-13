package org.example.ad.service;

import org.example.ad.DTO.ReviewAddDTO;
import org.example.ad.DTO.UserReviewDTO;
import org.example.ad.model.Review;

import java.util.List;

public interface ReviewService {
    List<UserReviewDTO> getReviewsByCameraId(Long cameraId);
    
    Review addReview(ReviewAddDTO reviewAddDTO, Long customerId);
    
    void deleteReview(Long reviewId, Long customerId);
    
    List<Review> findAllReviews();
    
    List<Review> findReviewsByCustomerId(Long customerId);
    
    void deleteReviewAsAdmin(Long reviewId);
}
