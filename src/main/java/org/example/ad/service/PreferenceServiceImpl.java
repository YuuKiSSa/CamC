package org.example.ad.service;

import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Preference;
import org.example.ad.model.Tag;
import org.example.ad.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Override
    public Preference recordVisit(Customer customer, Tag tag) {
        // Find existing preference or create a new one
        Preference preference = preferenceRepository.findByCustomerAndTag(customer, tag)
                .orElseGet(() -> {
                    Preference newPreference = new Preference();
                    newPreference.setCustomer(customer);
                    newPreference.setTag(tag);
                    return newPreference;
                });

        preference.setDate(LocalDate.now());
        preference.setAmount(preference.getAmount() + 1);  // Increment the visit count
        return preferenceRepository.save(preference);
    }

	@Override
	public List<Camera> findAllByPref(String username) {
		// TODO Auto-generated method stub
		return preferenceRepository.findByUserName(username);
	}
}
