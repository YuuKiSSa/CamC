package org.example.ad.service;

import org.example.ad.DTO.UserReviewDTO;

import java.util.List;

public interface ReviewService {
    List<UserReviewDTO> getReviewsByCameraId(Long cameraId);
}
