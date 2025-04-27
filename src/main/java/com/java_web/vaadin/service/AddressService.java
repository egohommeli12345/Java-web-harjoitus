package com.java_web.vaadin.service;

import com.java_web.vaadin.entities.Address;
import com.java_web.vaadin.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void save(Address address) {
        addressRepository.save(address);
    }
}
