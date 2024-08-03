package org.example.ad.service.impl;

import org.example.ad.DTO.CameraDetailDTO;
import org.example.ad.model.Camera;
import org.example.ad.repository.CameraRepository;
import org.example.ad.service.CameraDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CameraDetailServiceImpl implements CameraDetailService {

    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public CameraDetailDTO getCameraDetails(Long cameraId) {
        Optional<Camera> cameraOpt = cameraRepository.findById(cameraId);
        if (cameraOpt.isPresent()) {
            Camera camera = cameraOpt.get();
            CameraDetailDTO detailDTO = new CameraDetailDTO();
            detailDTO.setBrand(camera.getBrand().name());
            detailDTO.setModel(camera.getModel());
            detailDTO.setCategory(camera.getCategory().name());
            detailDTO.setDescription(camera.getDescription());
            detailDTO.setReleaseTime(camera.getReleaseTime());
            detailDTO.setInitialPrice(camera.getInitialPrice());
            detailDTO.setEffectivePixel(camera.getEffectivePixel());
            detailDTO.setISO(camera.getISO());
            detailDTO.setFocusPoint(camera.getFocusPoint());
            detailDTO.setContinuousShot(camera.getContinuousShot());
            detailDTO.setVideoResolution(camera.getVideoResolution());
            detailDTO.setVideoRate(camera.getVideoRate());
            return detailDTO;
        }
        return null;
    }
}
