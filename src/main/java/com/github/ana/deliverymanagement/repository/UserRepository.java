package com.github.ana.deliverymanagement.repository;

import com.github.ana.deliverymanagement.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <Users,Integer>{

    public Users findByEmail (String email);
}
