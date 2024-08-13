package org.example.ad.service.impl;

import org.example.ad.DTO.ReviewAddDTO;
import org.example.ad.DTO.ReviewDetailDTO;
import org.example.ad.DTO.UserReviewDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Review;
import org.example.ad.repository.CameraRepository;
import org.example.ad.repository.CustomerRepository;
import org.example.ad.repository.ReviewRepository;
import org.example.ad.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private CameraRepository cameraRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<UserReviewDTO> getReviewsByCameraId(Long cameraId) {
		List<Review> reviews = reviewRepository.findByCameraId(cameraId);

		return reviews.stream().collect(Collectors.groupingBy(review -> review.getCustomer().getId())).entrySet()
				.stream().map(entry -> {
					long userId = entry.getKey();
					List<ReviewDetailDTO> reviewDetails = entry.getValue().stream().map(review -> {
						ReviewDetailDTO detailDTO = new ReviewDetailDTO();
						detailDTO.setUserName(review.getCustomer().getUsername());
						detailDTO.setRating(review.getRate());
						detailDTO.setComment(review.getComment());
						detailDTO.setDate(review.getTime());
						return detailDTO;
					}).collect(Collectors.toList());

					UserReviewDTO userReviewDTO = new UserReviewDTO();
					userReviewDTO.setUserId(userId);
					userReviewDTO.setReviews(reviewDetails);
					return userReviewDTO;
				}).collect(Collectors.toList());
	}

	@Override
	public Review addReview(ReviewAddDTO reviewAddDTO, Long customerId) {
		Camera camera = cameraRepository.findById(reviewAddDTO.getCameraId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid camera ID"));
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));

		Review review = new Review();
		review.setCamera(camera);
		review.setCustomer(customer);
		review.setComment(reviewAddDTO.getComment());
		review.setRate(reviewAddDTO.getRate());
		review.setTime(LocalDateTime.now());

		return reviewRepository.save(review);
	}
	
	@Override
	public void deleteReview(Long reviewId, Long customerId) {
	    Review review = reviewRepository.findById(reviewId)
	            .orElseThrow(() -> new IllegalArgumentException("Review not found"));

	    if (review.getCustomer().getId() != customerId) {
	        throw new IllegalArgumentException("Unauthorized to delete this review");
	    }

	    reviewRepository.delete(review);
	}

}
