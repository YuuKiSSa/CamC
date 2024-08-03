package org.example.ad.service.impl;

import org.example.ad.DTO.MainDetailDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.Price;
import org.example.ad.model.Review;
import org.example.ad.repository.CameraRepository;
import org.example.ad.service.CameraMainDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CameraMainDetailServiceImpl implements CameraMainDetailService {

    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public MainDetailDTO getMainDetails(Long cameraId) {
        Optional<Camera> cameraOpt = cameraRepository.findById(cameraId);
        if (cameraOpt.isPresent()) {
            Camera camera = cameraOpt.get();
            MainDetailDTO mainDetailDTO = new MainDetailDTO();
            mainDetailDTO.setProductName(camera.getBrand().name() + " " + camera.getModel());

            // 计算平均评分
            List<Review> reviews = camera.getReviews();
            double averageRate = reviews.stream()
                                        .mapToDouble(Review::getRate)
                                        .average()
                                        .orElse(0.0);
            BigDecimal averageRateRounded = BigDecimal.valueOf(averageRate)
                                                      .setScale(1, RoundingMode.HALF_UP);
            mainDetailDTO.setAverageRate(averageRateRounded.doubleValue());

            // 获取最低价格
            double lowestPrice = camera.getPrices().stream()
                                       .mapToDouble(Price::getPrice)
                                       .min()
                                       .orElse(0.0);
            mainDetailDTO.setLowestPrice(lowestPrice);

            // 获取相机的第一个图片链接
            String imageUrl = camera.getCameraImages().stream()
                                    .findFirst()
                                    .map(image -> image.getUrl())
                                    .orElse("");
            mainDetailDTO.setImageUrl(imageUrl);

            return mainDetailDTO;
        }
        return null;
    }
}
