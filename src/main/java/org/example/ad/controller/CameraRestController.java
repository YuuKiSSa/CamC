package org.example.ad.controller;

import org.example.ad.DTO.CameraDetailDTO;
import org.example.ad.DTO.MainDetailDTO;
import org.example.ad.model.Camera;
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

	@GetMapping("/cameras/most-preferred/camera-id/{customerId}")
	public ResponseEntity<?> getMostPreferredCameraId(@PathVariable Long customerId) {
		Long cameraId = preferenceService.findMostPreferredCameraIdByCustomer(customerId);
		if (cameraId == null) {
			return ResponseEntity.notFound().build();
		}
		Camera c = customerService.findById(cameraId).get();
		return ResponseEntity.ok(c.getBrand() + " " + c.getModel());
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
}
