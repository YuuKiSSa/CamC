package org.example.ad.controller;

import org.example.ad.DTO.PlatformPriceHistoryDTO;
import org.example.ad.DTO.PriceDTO;
import org.example.ad.service.PriceHistoryService;
import org.example.ad.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PriceController {

    @Autowired
    private PriceService priceService;
    
    @Autowired
    private PriceHistoryService priceHistoryService;

    @GetMapping("/price/{id}")
    public ResponseEntity<List<PriceDTO>> getPriceByCameraId(@PathVariable Long id) {
        List<PriceDTO> prices = priceService.getPricesByCameraId(id);
        return ResponseEntity.ok(prices);
    }
    
    @GetMapping("/history/{id}")
    public ResponseEntity<List<PlatformPriceHistoryDTO>> getPriceHistoryByCameraId(@PathVariable Long id) {
        List<PlatformPriceHistoryDTO> history = priceHistoryService.getPriceHistoryByCameraId(id);
        if (history.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(history);
        }
    }
}
