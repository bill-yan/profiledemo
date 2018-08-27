package com.demo.profiledemo.service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo.profiledemo.model.Locale;
import com.demo.profiledemo.repository.LocaleRepository;

@Service
@RequiredArgsConstructor
public class LocaleService {

    @NonNull
    private final LocaleRepository localeRepository;

    @GraphQLQuery(name = "listAllEnabledLocales")
    public List<Locale> listAllEnabledLocales() {
        return localeRepository.findAllByEnabledTrue();
    }
}
