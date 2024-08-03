package org.example.ad.service;

import org.example.ad.DTO.PriceDTO;

import java.util.List;

public interface PriceService {
    List<PriceDTO> getPricesByCameraId(Long cameraId);
}
