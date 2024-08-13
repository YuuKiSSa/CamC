package org.example.ad.controller;

import org.example.ad.DTO.AllReviewDTO;
import org.example.ad.DTO.ReviewAddDTO;
import org.example.ad.DTO.UserReviewDTO;
import org.example.ad.model.Admin;
import org.example.ad.model.Customer;
import org.example.ad.model.Review;
import org.example.ad.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review/{id}")
    public ResponseEntity<List<UserReviewDTO>> getReviewsByCameraId(@PathVariable Long id) {
        List<UserReviewDTO> reviews = reviewService.getReviewsByCameraId(id);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(reviews);
        }
    }
    
    @GetMapping("/review/all")
    public ResponseEntity<List<AllReviewDTO>> getAllReviews(HttpSession session) {
        Object user = session.getAttribute("user");

        List<Review> reviews;

        if (user instanceof Admin) {
            reviews = reviewService.findAllReviews();
        } else if (user instanceof Customer) {
            Customer customer = (Customer) user;
            reviews = reviewService.findReviewsByCustomerId(customer.getId());
        } else {
            return ResponseEntity.status(401).body(null);
        }

        List<AllReviewDTO> reviewDTOs = reviews.stream()
                .map(review -> {
                    AllReviewDTO dto = new AllReviewDTO();
                    dto.setReviewId(review.getId());
                    dto.setComment(review.getComment());
                    dto.setRate(review.getRate());
                    dto.setTime(review.getTime());
                    dto.setCameraId(review.getCamera().getId());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(reviewDTOs);
    }

    
    @PostMapping("/add-review")
    public ResponseEntity<?> addReview(@RequestBody ReviewAddDTO reviewAddDTO, HttpSession session) {
        Customer currentUser = (Customer) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized - No user logged in");
        }

        Review review = reviewService.addReview(reviewAddDTO, currentUser.getId());
        return ResponseEntity.ok(review);
    }
    
    @DeleteMapping("/delete-review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId, HttpSession session) {
        Object currentUser = session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized - No user logged in");
        }

        if (currentUser instanceof Customer customer) {
            try {
                reviewService.deleteReview(reviewId, customer.getId());
                return ResponseEntity.ok("Review deleted successfully");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(400).body(e.getMessage());
            }
        } else if (currentUser instanceof Admin) {
            try {
                reviewService.deleteReviewAsAdmin(reviewId);
                return ResponseEntity.ok("Review deleted successfully by Admin");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(400).body(e.getMessage());
            }
        }

        return ResponseEntity.status(403).body("Forbidden");
    }

}
