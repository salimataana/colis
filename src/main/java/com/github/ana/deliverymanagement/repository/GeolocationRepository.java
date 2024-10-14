package com.github.ana.deliverymanagement.repository;

import com.github.ana.deliverymanagement.models.Geolocation;
import org.springframework.data.repository.CrudRepository;

public interface GeolocationRepository extends CrudRepository<Geolocation, Integer> {
}
