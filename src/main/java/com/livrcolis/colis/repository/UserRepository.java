package com.livrcolis.colis.repository;

import com.livrcolis.colis.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User,Integer>{
}
