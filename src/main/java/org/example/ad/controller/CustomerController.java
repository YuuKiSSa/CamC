package org.example.ad.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.service.CustomerService;
import org.example.ad.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PreferenceService preferenceService;

    @GetMapping("/list")
    public String customerDash(@RequestParam(defaultValue = "liangchang") String username,
                               @RequestParam(defaultValue = "liangchang") String password,
                               Model model) {
        System.out.println("Logged in as: " + username);
        model.addAttribute("cameras", customerService.findAll());
        List<Camera> allPrefs = preferenceService.findAllByPref(username);
        List<Camera> topThreePrefs = allPrefs.stream()
                .limit(3)
                .collect(Collectors.toList());
        model.addAttribute("tag", topThreePrefs);
        return "customer/productList";
    }

    @GetMapping("/camera/{id}")
    public String cameraDetail(@PathVariable Long id,
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
            return "customer/cameraDetail";
        } else {
            //System.out.println("Camera not found for id: " + id);
            return "error";
        }
    }
}