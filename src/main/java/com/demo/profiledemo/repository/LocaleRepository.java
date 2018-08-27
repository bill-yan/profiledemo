package com.demo.profiledemo.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.profiledemo.model.Locale;

@Repository
public interface LocaleRepository extends CrudRepository<Locale, Long> {

    List<Locale> findAllByEnabledTrue();

    Locale findByLocale(String locale);
}
