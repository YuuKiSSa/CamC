package org.example.ad.controller;

import java.util.Comparator;
import java.util.Optional;

import org.example.ad.DTO.CameraDetailDTO;
import org.example.ad.DTO.CameraListDTO;
import org.example.ad.DTO.MainDetailDTO;
import org.example.ad.DTO.MinPriceDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Price;
import org.example.ad.service.CameraDetailService;
import org.example.ad.service.CameraMainDetailService;
import org.example.ad.service.CustomerService;
import org.example.ad.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class CameraRestController {

	@Autowired
	private PreferenceService preferenceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CameraDetailService cameraDetailService;

	@Autowired
	private CameraMainDetailService cameraMainDetailService;

	@GetMapping("/cameras/most-preferred")
	public ResponseEntity<?> getMostPreferredCamera(HttpSession session) {
	    Customer currentUser = (Customer) session.getAttribute("user");
	    
	    if (currentUser == null) {
	        return ResponseEntity.status(401).body("Unauthorized - No user logged in");
	    }

	    Long cameraId = preferenceService.findMostPreferredCameraIdByCustomer(currentUser.getId());
	    
	    if (cameraId == null) {
	        return ResponseEntity.notFound().build();
	    }

	    Optional<Camera> cameraOpt = customerService.findById(cameraId);
	    if (cameraOpt.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    Camera camera = cameraOpt.get();

	    String imageUrl = customerService.findImageByCameraId(camera.getId());

	    Double latestLowestPrice = camera.getPrices().stream()
	            .filter(p -> p.getTime().equals(
	                camera.getPrices().stream()
	                    .max(Comparator.comparing(Price::getTime))
	                    .map(Price::getTime)
	                    .orElse(null)
	            ))
	            .min(Comparator.comparing(Price::getPrice)) 
	            .map(Price::getPrice)
	            .orElse(0.0); 

	    CameraListDTO cameraDTO = new CameraListDTO(camera, imageUrl);
	    cameraDTO.setLatestPrice(latestLowestPrice);

	    return ResponseEntity.ok(cameraDTO);
	}

	@GetMapping("/details/{id}")
	public ResponseEntity<?> getCameraDetails(@PathVariable Long id) {
		CameraDetailDTO cameraDetails = cameraDetailService.getCameraDetails(id);
		if (cameraDetails != null) {
			return ResponseEntity.ok(cameraDetails);
		} else {
			return ResponseEntity.status(404).body("Camera not found");
		}
	}
	
	@GetMapping("/main/{id}")
    public ResponseEntity<?> getMainDetails(@PathVariable Long id) {
        MainDetailDTO mainDetails = cameraMainDetailService.getMainDetails(id);
        if (mainDetails != null) {
            return ResponseEntity.ok(mainDetails);
        } else {
            return ResponseEntity.status(404).body("Camera not found");
        }
    }
	
	@GetMapping("/minPrice/{cameraId}")
    public ResponseEntity<MinPriceDTO> getMinPrice(@PathVariable Long cameraId) {
        MinPriceDTO minPrice = cameraDetailService.findMinPriceByCameraId(cameraId);
        if (minPrice != null) {
            return ResponseEntity.ok(minPrice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
