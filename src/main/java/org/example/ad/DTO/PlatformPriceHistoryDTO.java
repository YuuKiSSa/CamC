package org.example.ad.DTO;

import java.util.List;

public class PlatformPriceHistoryDTO {
    private String platform;
    private List<PriceHistoryDetailDTO> history;

    // Getters and setters
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<PriceHistoryDetailDTO> getHistory() {
        return history;
    }

    public void setHistory(List<PriceHistoryDetailDTO> history) {
        this.history = history;
    }
}
