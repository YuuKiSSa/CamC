package org.example.ad.service;

import java.util.List;

import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Preference;
import org.example.ad.model.Tag;

public interface PreferenceService {
    void recordVisits(Customer customer, List<Tag> tags);

	List<Camera> findAllByPref(String username);
}
