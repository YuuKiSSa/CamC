package org.example.ad.service;

import org.example.ad.DTO.PlatformPriceHistoryDTO;

import java.util.List;

public interface PriceHistoryService {
    List<PlatformPriceHistoryDTO> getPriceHistoryByCameraId(Long cameraId);
}
