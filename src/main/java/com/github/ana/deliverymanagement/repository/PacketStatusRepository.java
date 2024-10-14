package com.github.ana.deliverymanagement.repository;

import com.github.ana.deliverymanagement.models.PacketStatus;
import org.springframework.data.repository.CrudRepository;

public interface PacketStatusRepository extends CrudRepository<PacketStatus,Integer> {
}
