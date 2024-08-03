package org.example.ad.service.impl;

import org.example.ad.DTO.PriceDTO;
import org.example.ad.DTO.PriceDetailDTO;
import org.example.ad.model.CameraWebsite;
import org.example.ad.model.Price;
import org.example.ad.model.Platform;
import org.example.ad.repository.PriceRepository;
import org.example.ad.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<PriceDTO> getPricesByCameraId(Long cameraId) {
        List<Price> prices = priceRepository.findByCameraId(cameraId);

        // 按平台分组价格信息
        Map<Platform, List<Price>> groupedByPlatform = prices.stream()
                .collect(Collectors.groupingBy(Price::getPlatform));

        List<PriceDTO> priceDTOList = new ArrayList<>();
        for (Map.Entry<Platform, List<Price>> entry : groupedByPlatform.entrySet()) {
            Platform platform = entry.getKey();
            List<PriceDetailDTO> details = entry.getValue().stream()
                    .max(Comparator.comparing(Price::getTime))
                    .map(price -> {
                        String productName = price.getCamera().getBrand().name() + " " + price.getCamera().getModel();

                        // 获取相机的对应平台的链接
                        String websiteLink = price.getCamera().getCameraWebsites().stream()
                                .filter(cameraWebsite -> cameraWebsite.getPlatform() == platform)
                                .findFirst()
                                .map(CameraWebsite::getWebsite)
                                .orElse(""); 

                        return new PriceDetailDTO(
                            productName, 
                            price.getPrice(),
                            websiteLink 
                        );
                    })
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());

            priceDTOList.add(new PriceDTO(platform.name(), details));
        }
        return priceDTOList;
    }
}
