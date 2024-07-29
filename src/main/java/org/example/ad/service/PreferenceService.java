package org.example.ad.service;

import org.example.ad.model.Customer;
import org.example.ad.model.Preference;
import org.example.ad.model.Tag;

public interface PreferenceService {
    Preference recordVisit(Customer customer, Tag tag);
}
