package com.demo.profiledemo.service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo.profiledemo.model.Timezone;
import com.demo.profiledemo.repository.TimezoneRepository;

@Service
@RequiredArgsConstructor
public class TimezoneServie {

    @NonNull
    private final TimezoneRepository timezoneRepository;

    @GraphQLQuery(name = "listAllTimezones")
    public Iterable<Timezone> listAllTimezones() {
        return timezoneRepository.findAll();
    }
}
