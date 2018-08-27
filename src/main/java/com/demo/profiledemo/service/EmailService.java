package com.demo.profiledemo.service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo.profiledemo.model.Email;
import com.demo.profiledemo.repository.EmailRepository;

@Service
@RequiredArgsConstructor
public class EmailService {

    @NonNull
    private final EmailRepository emailRepository;

    @GraphQLMutation(name = "createEmail")
    public Email createEmail(@GraphQLArgument(name = "newEmail") Email newEmail) {
        return emailRepository.save(newEmail);
    }
}
