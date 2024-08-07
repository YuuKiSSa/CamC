package org.example.ad.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.ad.DTO.CameraDTO;
import org.example.ad.DTO.CameraListDTO;
import org.example.ad.DTO.CameraListWithTagDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.CameraImage;
import org.example.ad.model.Customer;
import org.example.ad.model.Favorite;
import org.example.ad.model.Price;
import org.example.ad.model.Review;
import org.example.ad.repository.FavoriteRepository;
import org.example.ad.service.CustomerService;
import org.example.ad.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.ad.DTO.FavoriteDTO;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PreferenceService preferenceService;

    
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> customerDash(HttpSession session) {

        Map<String, Object> response = new HashMap<>();
        List<CameraListDTO> cameras = customerService.findAll();

        response.put("cameras", cameras);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/list-tag")
    public ResponseEntity<List<CameraListWithTagDTO>> listAllCamerasWithTags() {
        List<CameraListWithTagDTO> camerasWithTags = customerService.findAllWithTags();
        return ResponseEntity.ok(camerasWithTags);
    }

    @GetMapping("/camera/{id}")
    public ResponseEntity<CameraDTO> cameraDetail(@PathVariable Long id, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("user");

        Optional<Camera> camera = customerService.findById(id);
        if (camera.isPresent()) {
            if (camera.get().getTags() != null && !camera.get().getTags().isEmpty() && customer != null) {
                preferenceService.recordVisits(customer, camera.get().getTags());
            }
            return ResponseEntity.ok(new CameraDTO(camera.get(), customerService.findImageByCameraId(camera.get().getId())));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/you-may-like")
    public ResponseEntity<List<CameraListDTO>> getTop3LikedCameras(HttpSession session) {
        Customer currentUser = (Customer) session.getAttribute("user");
        if (currentUser != null) {
            List<Camera> likedCameras = preferenceService.findTop3PreferredCamerasByCustomer(currentUser.getId());

            List<CameraListDTO> likedCamerasDTOs = likedCameras.stream()
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

                        double averageRateRounded = BigDecimal.valueOf(averageRate)
                                                              .setScale(1, RoundingMode.HALF_UP)
                                                              .doubleValue();
                        dto.setAverageRate(averageRateRounded);

                        Double latestLowestPrice = camera.getPrices().stream()
                                .filter(p -> p.getTime().equals(
                                    camera.getPrices().stream()
                                        .max(Comparator.comparing(Price::getTime)) 
                                        .map(Price::getTime)
                                        .orElse(null)
                                ))
                                .min(Comparator.comparing(Price::getPrice))
                                .map(Price::getPrice)
                                .orElse(null); 

                        if (latestLowestPrice != null) {
                            dto.setLatestPrice(latestLowestPrice);
                        }

                        return dto;
                    })
                    .toList();

            return ResponseEntity.ok(likedCamerasDTOs);
        } else {
            return ResponseEntity.status(401).build();
        }
    }





    @PostMapping("/favorite/add")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteDTO favoriteDTO, HttpSession session) {
        Customer currentUser = (Customer) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized - No user logged in");
        }

        try {
            Favorite favorite = customerService.addFavorite(currentUser.getId(), favoriteDTO.getCameraId(), favoriteDTO.getIdealPrice());
            return ResponseEntity.ok(favorite);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/favorite/delete")
    public ResponseEntity<?> deleteFavorite(@RequestBody FavoriteDTO favoriteDTO, HttpSession session) {
        Customer currentUser = (Customer) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized - No user logged in");
        }

        customerService.deleteFavorite(currentUser.getId(), favoriteDTO.getCameraId());

        return ResponseEntity.ok("Deleted from favorites");
    }

    @GetMapping("/favorite")
    public ResponseEntity<List<FavoriteDTO>> getFavorites(HttpSession session) {
        Customer currentUser = (Customer) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(401).body(null);
        }

        List<FavoriteDTO> favorites = customerService.findFavoritesByCustomerId(currentUser.getId());
        return ResponseEntity.ok(favorites);
    }
}
