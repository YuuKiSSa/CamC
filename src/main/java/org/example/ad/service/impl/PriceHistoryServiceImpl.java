package org.example.ad.service.impl;

import org.example.ad.DTO.PlatformPriceHistoryDTO;
import org.example.ad.DTO.PriceHistoryDetailDTO;
import org.example.ad.model.Price;
import org.example.ad.model.Platform;
import org.example.ad.repository.PriceRepository;
import org.example.ad.service.PriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<PlatformPriceHistoryDTO> getPriceHistoryByCameraId(Long cameraId) {
        List<Price> prices = priceRepository.findByCameraId(cameraId);

        Map<Platform, List<Price>> groupedByPlatform = prices.stream()
                .collect(Collectors.groupingBy(Price::getPlatform));

        return groupedByPlatform.entrySet().stream()
                .map(entry -> {
                    Platform platform = entry.getKey();
                    List<PriceHistoryDetailDTO> historyDetails = entry.getValue().stream()
                            .map(price -> {
                                PriceHistoryDetailDTO detailDTO = new PriceHistoryDetailDTO();
                                detailDTO.setDate(price.getTime());
                                detailDTO.setPrice(price.getPrice());
                                return detailDTO;
                            })
                            .collect(Collectors.toList());

                    PlatformPriceHistoryDTO platformPriceHistoryDTO = new PlatformPriceHistoryDTO();
                    platformPriceHistoryDTO.setPlatform(platform.name());
                    platformPriceHistoryDTO.setHistory(historyDetails);
                    return platformPriceHistoryDTO;
                })
                .collect(Collectors.toList());
    }
}
