package com.livrcolis.colis.repository;

import com.livrcolis.colis.models.Geolocalisation;
import org.springframework.data.repository.CrudRepository;

public interface GeolocalisationRepository extends CrudRepository<Geolocalisation, Integer> {
}
