package org.example.ad.service.impl;

import org.example.ad.DTO.CameraAddDTO;
import org.example.ad.DTO.CameraWebsiteDTO;
import org.example.ad.model.*;
import org.example.ad.repository.CameraRepository;
import org.example.ad.repository.CameraWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.example.ad.service.CameraService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraWebsiteRepository cameraWebsiteRepository;

    @Override
    public Camera addCamera(CameraAddDTO cameraAddDTO) {
        Camera camera = new Camera();
        camera.setBrand(Brand.valueOf(cameraAddDTO.getBrand()));
        camera.setModel(cameraAddDTO.getModel());
        camera.setCategory(Category.valueOf(cameraAddDTO.getCategory()));
        camera.setDescription(cameraAddDTO.getDescription());
        camera.setReleaseTime(cameraAddDTO.getReleaseTime());
        camera.setInitialPrice(cameraAddDTO.getInitialPrice());
        camera.setEffectivePixel(cameraAddDTO.getEffectivePixel());
        System.out.println("ISO from DTO: " + cameraAddDTO.getISO());

        camera.setISO(cameraAddDTO.getISO());
        camera.setFocusPoint(cameraAddDTO.getFocusPoint());
        camera.setContinuousShot(cameraAddDTO.getContinuousShot());
        camera.setVideoResolution(cameraAddDTO.getVideoResolution());
        camera.setVideoRate(cameraAddDTO.getVideoRate());

        List<CameraImage> cameraImages = new ArrayList<>();
        for (String imageUrl : cameraAddDTO.getImageUrls()) {
            CameraImage image = new CameraImage();
            image.setUrl(imageUrl);
            image.setCamera(camera);
            cameraImages.add(image);
        }
        camera.setCameraImages(cameraImages);

        List<CameraWebsite> cameraWebsites = new ArrayList<>();
        for (CameraWebsiteDTO websiteDTO : cameraAddDTO.getWebsites()) {
            CameraWebsite website = new CameraWebsite();
            website.setWebsite(websiteDTO.getWebsite());
            website.setPlatform(Platform.valueOf(websiteDTO.getPlatform()));
            website.setCamera(camera);
            cameraWebsites.add(website);
        }
        camera.setCameraWebsites(cameraWebsites);

        return cameraRepository.save(camera);
    }

    @Override
    public void deleteCamera(Long cameraId) {
        cameraRepository.deleteById(cameraId);
    }
}
