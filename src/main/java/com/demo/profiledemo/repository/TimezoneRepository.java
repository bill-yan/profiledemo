package com.demo.profiledemo.repository;

import org.springframework.data.repository.CrudRepository;
import com.demo.profiledemo.model.Timezone;

public interface TimezoneRepository extends CrudRepository<Timezone, Long> {

    Timezone findByName(String name);
}

