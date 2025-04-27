package com.java_web.vaadin.service;

import com.java_web.vaadin.entities.Store;
import com.java_web.vaadin.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public void save(Store store) {
        storeRepository.save(store);
    }

    public List<Store> findAll() {
        return storeRepository.findAll();
    }
}
