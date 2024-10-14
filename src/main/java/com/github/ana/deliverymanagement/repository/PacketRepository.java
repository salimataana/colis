package com.github.ana.deliverymanagement.repository;

import com.github.ana.deliverymanagement.models.Packet;
import org.springframework.data.repository.CrudRepository;

public interface PacketRepository extends CrudRepository<Packet,Integer> {
}
