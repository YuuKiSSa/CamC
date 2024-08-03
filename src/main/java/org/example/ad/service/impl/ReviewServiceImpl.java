package org.example.ad.service.impl;

import org.example.ad.DTO.ReviewDetailDTO;
import org.example.ad.DTO.UserReviewDTO;
import org.example.ad.model.Review;
import org.example.ad.repository.ReviewRepository;
import org.example.ad.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<UserReviewDTO> getReviewsByCameraId(Long cameraId) {
        List<Review> reviews = reviewRepository.findByCameraId(cameraId);

        // 按用户ID分组评论信息
        return reviews.stream()
                .collect(Collectors.groupingBy(review -> review.getCustomer().getId()))
                .entrySet()
                .stream()
                .map(entry -> {
                    long userId = entry.getKey();
                    List<ReviewDetailDTO> reviewDetails = entry.getValue().stream()
                            .map(review -> {
                                ReviewDetailDTO detailDTO = new ReviewDetailDTO();
                                detailDTO.setUserName(review.getCustomer().getUsername());
                                detailDTO.setRating(review.getRate());  
                                detailDTO.setComment(review.getComment());
                                detailDTO.setDate(review.getTime());  
                                return detailDTO;
                            })
                            .collect(Collectors.toList());

                    UserReviewDTO userReviewDTO = new UserReviewDTO();
                    userReviewDTO.setUserId(userId);
                    userReviewDTO.setReviews(reviewDetails);
                    return userReviewDTO;
                })
                .collect(Collectors.toList());
    }
}
