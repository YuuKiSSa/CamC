package org.example.ad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.ad.DTO.CameraDTO;
import org.example.ad.DTO.CameraListDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.service.CustomerService;
import org.example.ad.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PreferenceService preferenceService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> customerDash(
            @RequestParam(defaultValue = "liangchang") String username,
            @RequestParam(defaultValue = "liangchang") String password,
            Model model) {

        Map<String, Object> response = new HashMap<>();
        List<CameraListDTO> cameras = customerService.findAll();

        response.put("cameras", cameras);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/camera/{id}")
    public ResponseEntity<CameraDTO> cameraDetail(@PathVariable Long id,
                                                  @RequestParam(defaultValue = "liangchang") String username,
                                                  @RequestParam(defaultValue = "liangchang") String password,
                                                  Model model) {
        //System.out.println("Accessing camera detail for id: " + id + " as user: " + username);
        Optional<Camera> camera = customerService.findById(id);
        if (camera.isPresent()) {
            model.addAttribute("camera", camera.get());

            Customer customer = customerService.findByUsername(username).orElse(null);
            if (customer != null && camera.get().getTags() != null && !camera.get().getTags().isEmpty()) {
                preferenceService.recordVisits(customer, camera.get().getTags());
                //System.out.println("Recorded visit for customer: " + username + " to camera with multiple tags.");
            }

            return ResponseEntity.ok(new CameraDTO(camera.get(), customerService.findImageByCameraId(camera.get().getId())));
        } else {
            //System.out.println("Camera not found for id: " + id);
            return ResponseEntity.badRequest().build();
        }
    }
}
