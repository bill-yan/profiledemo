package com.demo.profiledemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.profiledemo.model.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {

}
