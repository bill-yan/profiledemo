package com.demo.profiledemo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.profiledemo.model.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email, Long> {

    Optional<Email> findByEmail(String email);
}
