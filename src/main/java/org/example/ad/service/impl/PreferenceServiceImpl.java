package org.example.ad.service.impl;

import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Preference;
import org.example.ad.model.Tag;
import org.example.ad.repository.CustomerRepository;
import org.example.ad.repository.PreferenceRepository;
import org.example.ad.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void recordVisits(Customer customer, List<Tag> tags) {
        tags.forEach(tag -> {
            Preference preference = preferenceRepository.findByCustomerAndTag(customer, tag)
                    .orElseGet(() -> {
                        Preference newPreference = new Preference();
                        newPreference.setCustomer(customer);
                        newPreference.setTag(tag);
                        return newPreference;
                    });

            preference.setDate(LocalDate.now());
            preference.setAmount(preference.getAmount() + 1);
            preferenceRepository.save(preference);
        });
    }

	@Override
	public List<Camera> findAllByPref(String username) {
		return preferenceRepository.findByUserName(username);
	}
	
	@Override
	public Long findMostPreferredCameraIdByCustomer(Long customerId) {
	    List<Object[]> results = preferenceRepository.findMostPreferredCameraIdByCustomer(customerId);
	    if (!results.isEmpty()) {
	        return (Long) results.get(0)[0]; 
	    }
	    return null; 
	}
	
	@Override
	public List<Camera> findTop3PreferredCamerasByCustomer(Long customerId) {
	    List<Camera> cameras = preferenceRepository.findByUserName(customerRepository.findById(customerId).get().getUsername());
	    return cameras.stream().limit(3).toList();
	}


}
