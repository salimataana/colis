package com.livrcolis.colis.repository;

import com.livrcolis.colis.models.Packet;
import org.springframework.data.repository.CrudRepository;

public interface PacketRepository extends CrudRepository<Packet,Integer> {
}
