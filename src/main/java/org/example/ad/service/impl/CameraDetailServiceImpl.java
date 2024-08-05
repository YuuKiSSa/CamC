package org.example.ad.service.impl;

import org.example.ad.DTO.CameraDetailDTO;
import org.example.ad.DTO.MinPriceDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.Price;
import org.example.ad.repository.CameraRepository;
import org.example.ad.repository.PriceRepository;
import org.example.ad.service.CameraDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CameraDetailServiceImpl implements CameraDetailService {

    @Autowired
    private CameraRepository cameraRepository;
    
    @Autowired
    private PriceRepository priceRepository;

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
    
    public MinPriceDTO findMinPriceByCameraId(Long cameraId) {
        List<Price> prices = priceRepository.findByCameraId(cameraId);

        var latestPricesByPlatform = prices.stream()
                .collect(Collectors.groupingBy(
                        Price::getPlatform,
                        Collectors.maxBy(Comparator.comparing(Price::getTime))
                ));

        Price minPrice = latestPricesByPlatform.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .min(Comparator.comparing(Price::getPrice))
                .orElse(null);

        if (minPrice == null) {
            return null;
        }

        MinPriceDTO minPriceDTO = new MinPriceDTO();
        minPriceDTO.setPlatform(minPrice.getPlatform().name());
        minPriceDTO.setPrice(minPrice.getPrice());
        return minPriceDTO;
    }
}
