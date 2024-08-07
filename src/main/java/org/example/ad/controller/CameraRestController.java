package org.example.ad.controller;

import org.example.ad.model.Camera;
import org.example.ad.service.CustomerService;
import org.example.ad.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cameras")
public class CameraRestController {

	@Autowired
    private PreferenceService preferenceService;
	@Autowired
    private CustomerService customerService;
	

	@GetMapping("/most-preferred/camera-id/{customerId}")
	public ResponseEntity<?> getMostPreferredCameraId(@PathVariable Long customerId) {
	    Long cameraId = preferenceService.findMostPreferredCameraIdByCustomer(customerId);
	    if (cameraId == null) {
	        return ResponseEntity.notFound().build();
	    }
	    Camera c=customerService.findById(cameraId).get();
	    return ResponseEntity.ok(c.getBrand()+" "+c.getModel());
	}
	

}
