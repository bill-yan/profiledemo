package com.demo.profiledemo.service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo.profiledemo.model.Phone;
import com.demo.profiledemo.repository.PhoneRepository;

@Service
@RequiredArgsConstructor
public class PhoneService {

    @NonNull
    private final PhoneRepository phoneRepository;

    @GraphQLMutation(name = "createPhone")
    public Phone createPhone(@GraphQLArgument(name = "newPhone") Phone newPhone) {
        return phoneRepository.save(newPhone);
    }
}
