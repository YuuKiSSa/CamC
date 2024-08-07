package org.example.ad.service.impl;

import org.example.ad.DTO.CameraListDTO;
import org.example.ad.DTO.CameraListWithTagDTO;
import org.example.ad.DTO.FavoriteDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.CameraImage;
import org.example.ad.model.Customer;
import org.example.ad.model.Favorite;
import org.example.ad.model.Review;
import org.example.ad.model.Tag;
import org.example.ad.repository.CameraImageRepository;
import org.example.ad.repository.CameraRepository;
import org.example.ad.repository.CustomerRepository;
import org.example.ad.repository.FavoriteRepository;
import org.example.ad.repository.PriceRepository;
import org.example.ad.repository.TagRepository;
import org.example.ad.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private CameraImageRepository cameraImageRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;
    
    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<CameraListDTO> findAll() {
        return cameraRepository.findAll().stream()
                .map(camera -> {
                    String imageUrl = camera.getCameraImages().stream()
                                            .findFirst() 
                                            .map(CameraImage::getUrl)
                                            .orElse(""); 

                    CameraListDTO dto = new CameraListDTO(camera, imageUrl);

                    List<Review> reviews = camera.getReviews();
                    double averageRate = reviews.stream()
                                                .mapToDouble(Review::getRate)
                                                .average()
                                                .orElse(0.0);
                    BigDecimal averageRateRounded = BigDecimal.valueOf(averageRate)
                                                              .setScale(1, RoundingMode.HALF_UP);
                    dto.setAverageRate(averageRateRounded.doubleValue());

                    Double latestPrice = priceRepository.findLatestLowestPriceByCameraId(camera.getId());
                    dto.setLatestPrice(latestPrice);

                    return dto;
                })
                .toList();
    }


    
    @Override
    public List<CameraListWithTagDTO> findAllWithTags() {
        return cameraRepository.findAll().stream()
                .map(c -> {
                    List<String> tags = c.getTags().stream()
                            .map(Tag::getCategory)
                            .toList();
                    
                    String imageUrl = c.getCameraImages().stream()
                            .findFirst()
                            .map(CameraImage::getUrl)
                            .orElse("");
                    
                    Double latestPrice = priceRepository.findLatestLowestPriceByCameraId(c.getId());

                    CameraListWithTagDTO dto = new CameraListWithTagDTO(c, imageUrl, tags);
                    dto.setLatestPrice(latestPrice);
                    return dto;
                })
                .toList();
    }

    @Override
    public Optional<Camera> findById(Long id) {
        return cameraRepository.findById(id);
    }
    
    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public String findImageByCameraId(Long id) {
        return cameraRepository.findById(id)
                .flatMap(camera -> camera.getCameraImages().stream().findFirst())
                .map(CameraImage::getUrl)
                .orElse(""); 
    }

    @Override
    public Favorite addFavorite(Long customerId, Long cameraId, Double idealPrice) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        Optional<Camera> cameraOpt = cameraRepository.findById(cameraId);

        if (customerOpt.isPresent() && cameraOpt.isPresent()) {
            Favorite favorite = new Favorite();
            favorite.setCustomer(customerOpt.get());
            favorite.setCamera(cameraOpt.get());
            favorite.setIdealPrice(idealPrice);
            return favoriteRepository.save(favorite);
        } else {
            throw new IllegalArgumentException("Invalid customer or camera ID");
        }
    }
    
    @Transactional
    @Override
    public void deleteFavorite(Long customerId, Long cameraId) {
        favoriteRepository.deleteByCameraIdAndCustomerId(cameraId, customerId);
    }
    
    @Override
    public List<FavoriteDTO> findFavoritesByCustomerId(Long customerId) {
        List<Favorite> favorites = favoriteRepository.findByCustomerId(customerId);
        return favorites.stream()
                .map(favorite -> {
                    FavoriteDTO dto = new FavoriteDTO();
                    dto.setCameraId(favorite.getCamera().getId());
                    dto.setIdealPrice(favorite.getIdealPrice());
                    return dto;
                })
                .toList();
    }
}
