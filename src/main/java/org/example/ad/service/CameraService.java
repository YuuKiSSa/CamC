package org.example.ad.service;

import org.example.ad.DTO.CameraAddDTO;
import org.example.ad.model.Camera;

public interface CameraService {
    Camera addCamera(CameraAddDTO cameraAddDTO);
    void deleteCamera(Long cameraId);
}
