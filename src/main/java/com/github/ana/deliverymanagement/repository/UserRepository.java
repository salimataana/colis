package com.github.ana.deliverymanagement.repository;

import com.github.ana.deliverymanagement.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User,Integer>{
}
