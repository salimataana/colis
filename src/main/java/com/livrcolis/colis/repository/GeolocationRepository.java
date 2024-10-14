package com.livrcolis.colis.repository;

import com.livrcolis.colis.models.Geolocation;
import org.springframework.data.repository.CrudRepository;

public interface GeolocationRepository extends CrudRepository<Geolocation, Integer> {
}
