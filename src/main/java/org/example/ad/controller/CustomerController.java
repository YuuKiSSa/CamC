package org.example.ad.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.ad.DTO.CameraDTO;
import org.example.ad.DTO.CameraListDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.CameraImage;
import org.example.ad.model.Customer;
import org.example.ad.model.Price;
import org.example.ad.service.CustomerService;
import org.example.ad.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/camera/{id}")
    public ResponseEntity<CameraDTO> cameraDetail(@PathVariable Long id, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("user");

        Optional<Camera> camera = customerService.findById(id);
        if (camera.isPresent() && customer!=null) {
            if (camera.get().getTags() != null && !camera.get().getTags().isEmpty()) {
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
                        CameraListDTO dto = new CameraListDTO(camera, camera.getCameraImages().stream().findFirst().map(CameraImage::getUrl).orElse(""));

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



}
