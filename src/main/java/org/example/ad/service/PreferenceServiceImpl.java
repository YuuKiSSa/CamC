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
import java.util.Optional;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    @Autowired
    private PreferenceRepository preferenceRepository;

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
		// TODO Auto-generated method stub
		return preferenceRepository.findByUserName(username);
	}
	
	@Override
	public Long findMostPreferredCameraIdByCustomer(Long customerId) {
	    List<Object[]> results = preferenceRepository.findMostPreferredCameraIdByCustomer(customerId);
	    if (!results.isEmpty()) {
	        return (Long) results.get(0)[0]; // 获取列表中的第一个元素，即cameraId
	    }
	    return null; // 如果没有找到任何相机，返回null
	}


}
