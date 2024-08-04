package org.example.ad.service;

import org.example.ad.DTO.CameraDetailDTO;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface CameraDetailService {
	public CameraDetailDTO getCameraDetails(Long cameraId);
}